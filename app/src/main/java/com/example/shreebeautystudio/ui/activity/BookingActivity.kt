package com.example.shreebeautystudio.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.model.BookingModel
import com.example.shreebeautystudio.repository.BookingRepositoryImplement
import com.example.shreebeautystudio.viewmodel.BookingViewModel

class BookingActivity<EditText> : AppCompatActivity() {


    private lateinit var editDate: EditText
    private lateinit var editTime: EditText
    private lateinit var btnConfirmBooking: Button
    private lateinit var bookingViewModel: BookingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)


        editDate = findViewById(R.id.editDate)
        editTime = findViewById(R.id.editTime)
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)

        val repo = BookingRepositoryImplement()
        bookingViewModel = BookingViewModel(repo)

        btnConfirmBooking.setOnClickListener {
            val date = editDate.text.toString()
            val time = editTime.text.toString()
            val userId = "currentUser Id" // Replace with actual user ID

            val booking = BookingModel(userId = userId, date = date, time = time)
            bookingViewModel.createBooking(booking) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (success) {
                    finish() // Close the activity after booking
                }
            }
        }
    }
}
