package com.diploa.work.prdoductdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diploa.work.prdoductdetail.domain.GetTopProductDetailsUseCase
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PRODUCT_ID = "productId"

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getTopProductDetailsUseCase: GetTopProductDetailsUseCase
) : ViewModel() {

    private val _productDetail = MutableStateFlow(TopProductItem())
    val productDetails = _productDetail.asStateFlow()

    init {
        viewModelScope.launch {
            getTopProductDetailsUseCase(checkNotNull(savedStateHandle[PRODUCT_ID])).collect {
                _productDetail.value = it
            }
        }
    }
}