package com.diploma.work.prdoductdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.booking.domain.SaveBookedProductUseCase
import com.diploma.work.prdoductdetail.domain.GetProductDetailsUseCase
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.common.domain.GetBookedProductByIdUseCase
import com.diploma.work.common.domain.GetTopProductsUseCase
import com.diploma.work.common.domain.IsProductInFavoritesUseCase
import com.diploma.work.common.domain.SaveToFavoriteProductUseCase
import com.diploma.work.prdoductdetail.domain.IsProductBookedUseCase
import com.diploma.work.repository.data.BookedProduct
import com.diploma.work.repository.data.ProductDetailsData
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PRODUCT_ID = "productId"

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val isProductInFavoritesUseCase: IsProductInFavoritesUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val isProductBookedUseCase: IsProductBookedUseCase,
    private val saveBookedProductUseCase: SaveBookedProductUseCase,
    private val getBookedProductById: GetBookedProductByIdUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(ProductDetailUiState())

    private val productId by lazy { checkNotNull(savedStateHandle.get<String>(PRODUCT_ID)) }
    private val isBookingClicked = MutableStateFlow(false)
    private val showBookedBottomSheet = MutableStateFlow(false)
    private val showDataPicker = MutableStateFlow(false)
    private val isProductBooked = MutableStateFlow(false)
    private val bookedProductDate = MutableStateFlow<Long>(0)

    init {
        getBookedProductDetail()
        isProductBooked()
        combineFlow()
    }

    private fun combineFlow() {
        viewModelScope.launch {
            combine(
                getProductDetailsUseCase(productId.toInt()),
                getTopProductsUseCase(),
                flowOf(isProductInFavoritesUseCase(productId.toInt())),
                isBookingClicked,
                isProductBooked,
                bookedProductDate,
                showBookedBottomSheet,
                showDataPicker
            ) { flows ->
                ProductDetailUiState(
                    productDetail = flows[0] as ProductDetailsData?,
                    topProductsList = flows[1] as List<TopProductItem>,
                    isProductInFavorites = flows[2] as Boolean,
                    isBookingClicked = flows[3] as Boolean,
                    isProductBooked = flows[4] as Boolean,
                    bookedProductDate = flows[5] as Long?,
                    showBookedBottomSheet = flows[6] as Boolean,
                    showDataPicker = flows[7] as Boolean
                )
            }.collect {
                uiState.value = it
            }
        }
    }

    fun saveProductToFavorites(productItem: ProductDetailsData) = viewModelScope.launch {
        saveToFavoriteProductUseCase(productItem.asFavoriteProduct())
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun bookingClicked() {
        isBookingClicked.value = true
        isProductBooked()
    }

    fun finishBookingLogic() {
        isBookingClicked.value = false
        resetBookingShowStates()
    }

    fun saveBookedProduct(bookedProduct: BookedProduct) = viewModelScope.launch {
        saveBookedProductUseCase(BookedProduct(bookedProduct.productId, bookedProduct.bookedDate))
        getBookedProductDetail()
        isProductBooked()
    }

    fun onRebookClicked() {
        showBookedBottomSheet.value = false
        showDataPicker.value = true
    }

    private fun getBookedProductDetail() = viewModelScope.launch {
        bookedProductDate.value = try {
            getBookedProductById(productId.toInt()).firstOrNull() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun isProductBooked() {
        viewModelScope.launch {
            isProductBooked.value = isProductBookedUseCase(productId.toInt()) == true
            if (isProductBooked.value) {
                showBookedBottomSheet.value = true
            } else {
                showDataPicker.value = true
            }
        }
    }

    private fun resetBookingShowStates() {
        showBookedBottomSheet.value = false
        showDataPicker.value = false
    }
}
