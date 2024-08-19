package com.diploma.work.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.searchImageSize
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.repository.data.AllProductsItem

@Composable
internal fun SearchContent(
    productsList: List<AllProductsItem>,
    onProductClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = normal100),
    ) {
        items(productsList) { product ->
            SearchItem(
                product = product,
                onProductClicked = onProductClicked
            )
            Divider()
        }
    }
}

@Composable
private fun SearchItem(
    product: AllProductsItem,
    onProductClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().clickable { product.id?.let { onProductClicked(it) } },
        horizontalArrangement = Arrangement.spacedBy(
            small100,
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(small100),
            modifier = Modifier.padding(vertical = small150)
        ) {
            AsyncImage(
                model = product.images?.firstOrNull()?.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(searchImageSize)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(small100)) {
            Text(product.title.orEmpty())
            PriceAndDate(
                product.priceOnSale.toString(),
                product.saleEndsDate.toString()
            )
        }
    }
}

@Composable
private fun PriceAndDate(
    priceOnSale: String,
    saleEndsDate: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.price, priceOnSale),
            modifier = Modifier.weight(1f)
        )
        Text(text = stringResource(R.string.end_date, saleEndsDate))
    }
}