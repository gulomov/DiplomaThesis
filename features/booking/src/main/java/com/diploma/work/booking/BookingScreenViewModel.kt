package com.diploma.work.booking

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookingScreenViewModel @Inject constructor() : ViewModel() {
    private val _showCalendar = MutableStateFlow(false)
    val showCalendar = _showCalendar.asStateFlow()

    init {
        showCalendar()
    }

    private fun showCalendar() {
        _showCalendar.value = true
    }

    fun closeCalendar() {
        _showCalendar.value = false
    }
}