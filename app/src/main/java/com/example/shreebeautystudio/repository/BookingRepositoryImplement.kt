package com.example.shreebeautystudio.repository

import com.example.shreebeautystudio.model.BookingModel
import com.google.firebase.database.FirebaseDatabase

class BookingRepositoryImplement : BookingRepository {
    private val database = FirebaseDatabase.getInstance()
    private val ref = database.reference.child("bookings")

    override fun createBooking(booking: BookingModel, callback: (Boolean, String) -> Unit) {
        val id = ref.push().key.toString()
        booking.id = id
        ref.child(id).setValue(booking).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Booking created successfully")
            } else {
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun getBookingsByUser Id(userId: String, callback: (List<BookingModel>?, Boolean, String) -> Unit) {
        ref.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bookings = mutableListOf<BookingModel>()
                if (snapshot.exists()) {
                    for (each in snapshot.children) {
                        val booking = each.getValue(BookingModel::class.java)
                        if (booking != null) {
                            bookings.add(booking)
                        }
                    }
                    callback(bookings, true, "Bookings fetched successfully")
                } else {
                    callback(null, false, "No bookings found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null, false, error.message)
            }
        })
    }
}