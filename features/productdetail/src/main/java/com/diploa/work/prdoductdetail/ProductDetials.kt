package com.diploa.work.prdoductdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductDetails(viewModel: ProductDetailsViewModel = hiltViewModel()) {
    Text(text = "Hello details. Id = ${viewModel.productId}")
}