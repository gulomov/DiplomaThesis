import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.diploma.work.common.componants.GenericProductImages
import com.diploma.work.common.componants.Prices
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> GenericProductItem(
    item: T,
    onClick: (T) -> Unit,
    onSaveOrDeleteClick: (Boolean) -> Unit,
    productImagesList: List<String>,
    productPercentage: String,
    title: String,
    originalPrice: String,
    priceOnSale: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onClick(item) },
        modifier = modifier.padding(normal100),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            GenericProductImages(
                imageUrls = productImagesList,
                isFavorite = isFavorite,
                productPercentage = productPercentage,
                onSaveClick = {
                    onSaveOrDeleteClick(it)
                }
            )
            Spacer(modifier = Modifier.height(small100))
            Text(
                text = title,
                modifier = Modifier
                    .padding(small100)
                    .align(Alignment.Start),
                maxLines = 1
            )
            Prices(
                originalPrice = originalPrice,
                priceOnSale = priceOnSale,
                modifier = Modifier.padding(start = small100),
                textColor = Color.White
            )
            Spacer(modifier = Modifier.height(small100))
        }
    }
}
