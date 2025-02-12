package com.example.shreebeautystudio.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.databinding.ActivityForgetBinding
import com.example.shreebeautystudio.repository.UserRepository
import com.example.shreebeautystudio.repository.UserRepositoryImplement
import com.example.shreebeautystudio.viewmodel.UserViewModel

class ForgetActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgetBinding
   lateinit var userViewModel: UserViewModel


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivityForgetBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var repo = UserRepositoryImplement()
            userViewModel = UserViewModel(repo)

            binding.forgetButton.setOnClickListener {
                val email = binding.forgetEmail.text.toString()
                if (email.isEmpty()) {
                    binding.forgetEmail.error = "Email cannot be empty"
                }

                userViewModel.forgetPassword(email) { success, message ->
                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }

                }
            }

            setContentView(R.layout.activity_forget)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameLayout)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
