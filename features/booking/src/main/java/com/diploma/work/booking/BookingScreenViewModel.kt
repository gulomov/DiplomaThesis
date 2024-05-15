package com.diploma.work.booking

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.booking.domain.SaveBookedProductUseCase
import com.diploma.work.common.domain.GetBookedProductByIdUseCase
import com.diploma.work.repository.data.BookedProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BookingScreenViewModel @Inject constructor(
    private val saveBookedProductUseCase: SaveBookedProductUseCase,
    private val getBookedProductById: GetBookedProductByIdUseCase,
) : ViewModel() {

    private val _bookedProductDate = MutableStateFlow<Long>(0)
    val bookedProductDate = _bookedProductDate.asStateFlow()

    fun saveBookedProduct(productId: Int, bookedDate: Long) = viewModelScope.launch {
        saveBookedProductUseCase(BookedProduct(productId, bookedDate))
    }

    fun getBookedProductDetail(productId: Int) = viewModelScope.launch {
        getBookedProductById(productId).firstOrNull()?.bookedDate.apply {
            _bookedProductDate.value = this ?: 0
        }
    }
}