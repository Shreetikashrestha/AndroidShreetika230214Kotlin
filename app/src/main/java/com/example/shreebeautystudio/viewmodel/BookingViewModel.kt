package com.example.shreebeautystudio.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.shreebeautystudio.model.BookingModel
import com.example.shreebeautystudio.repository.BookingRepository

class BookingViewModel(private val repo: BookingRepository) {
    private val _bookings = MutableLiveData<List<BookingModel>>()
    val bookings = _bookings

    fun createBooking(booking: BookingModel, callback: (Boolean, String) -> Unit) {
        repo.createBooking(booking, callback)
    }

    fun getBookingsByUser Id(userId: String) {
        repo.getBookingsByUser Id(userId) { bookings, success, message ->
            if (success) {
                _bookings.value = bookings
            } else {
                _bookings.value = emptyList()
            }
        }
    }
}