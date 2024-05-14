package com.diploma.work.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.booking.domain.SaveBookedProductUseCase
import com.diploma.work.repository.data.BookedProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingScreenViewModel @Inject constructor(
    private val saveBookedProductUseCase: SaveBookedProductUseCase
) : ViewModel() {
    private val _showCalendar = MutableStateFlow(false)
    val showCalendar = _showCalendar.asStateFlow()

    init {
        showCalendar()
    }

    fun saveBookedProduct(productId: Int, bookedDate: Long) = viewModelScope.launch {
        saveBookedProductUseCase(BookedProduct(productId, bookedDate))
    }

    private fun showCalendar() {
        _showCalendar.value = true
    }

    fun closeCalendar() {
        _showCalendar.value = false
    }
}