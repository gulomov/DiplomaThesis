@file:OptIn(ExperimentalMaterial3Api::class)

package com.diploma.work.comingsoon

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100

private const val CARD_SIZE = 0.25f

@Composable
fun ComingSoonScreen(
    modifier: Modifier = Modifier,
    viewModel: ComingSoonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(small100),
        contentPadding = PaddingValues(normal100)
    ) {
        items(uiState.listOfComingProducts) {
            ComingSoonProduct(
                imageUrl = it.image.toString(),
                startingDate = it.startingDate.toString(),
                onProductClicked = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComingSoonProduct(
    imageUrl: String,
    startingDate: String,
    onProductClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val cardHeight = screenHeight * CARD_SIZE

    Card(
        modifier = modifier
            .height(cardHeight)
            .fillMaxWidth(),
        shape = RoundedCornerShape(small100),
        onClick = onProductClicked,
    ) {
        Box(modifier = Modifier.fillMaxSize())
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.7f))
            )
            Text(
                color = Color.Black,
                text = stringResource(R.string.coming_date, startingDate),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .padding(normal100)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ComingSoonPreview() {
    MaterialTheme {
        ComingSoonProduct(
            imageUrl = "https://logocreator.io/wp-content/uploads/2023/11/lacoste-brand-logo-symbol-with-name-design-clothes-fashion-illustration-with-green-background-free-vector.jpg",
            startingDate = "",
            onProductClicked = {}
        )
    }
}