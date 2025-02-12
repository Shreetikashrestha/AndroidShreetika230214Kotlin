package com.example.shreebeautystudio.repository

import com.example.shreebeautystudio.model.BookingModel

interface BookingRepository {
      fun createBooking(booking: BookingModel, callback: (Boolean, String) -> Unit)
    fun getBookingsByUser Id(userId: String, callback: (List<BookingModel>?, Boolean, String) -> Unit)


}