package com.diploa.work.prdoductdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploa.work.prdoductdetail.domain.GetProductDetailsUseCase
import com.diploma.work.common.domain.GetTopProductsUseCase
import com.diploma.work.repository.data.ProductDetailsData
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val PRODUCT_ID = "productId"

@HiltViewModel
class ProductDetailsViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        getProductDetailsUseCase: GetProductDetailsUseCase,
        private val getTopProductsUseCase: GetTopProductsUseCase,
    ) : ViewModel() {
        private val _productDetail = MutableStateFlow(ProductDetailsData())
        val productDetails = _productDetail.asStateFlow()

        private val _topProductsList = MutableStateFlow(listOf<TopProductItem>())
        val topProductsList = _topProductsList.asStateFlow()

        init {
            viewModelScope.launch {
                getProductDetailsUseCase(checkNotNull(savedStateHandle[PRODUCT_ID])).collect {
                    _productDetail.value = it
                }
            }
        }

        fun getTopProductsList() {
            viewModelScope.launch {
                getTopProductsUseCase().collect { data ->
                    if (data.isNotEmpty()) {
                        _topProductsList.value = data
                    } else {
                        Timber.e("Top products is empty")
                    }
                }
            }
        }
    }
