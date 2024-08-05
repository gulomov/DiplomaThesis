package com.diploma.work.prdoductdetail

import com.diploma.work.repository.data.ProductDetailsData
import com.diploma.work.repository.data.TopProductItem

data class ProductDetailUiState(
    val productDetail: ProductDetailsData? = null,
    val topProductsList: List<TopProductItem> = emptyList(),
    val bookedProductDate: Long? = 0,
    val isProductInFavorites: Boolean = false,
    val isBookingClicked: Boolean = false,
    val isProductBooked: Boolean = false,
    val showBookedBottomSheet: Boolean = false,
    val showDataPicker: Boolean = false
)
