package com.example.shreebeautystudio.ui.activity



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.databinding.ActivityLoginBinding
import com.example.shreebeautystudio.databinding.ActivityRegistrationBinding
import com.example.shreebeautystudio.model.UserModel
import com.example.shreebeautystudio.repository.UserRepositoryImplement
import com.example.shreebeautystudio.utils.LoadingUtils
import com.example.shreebeautystudio.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    lateinit var userViewModel: UserViewModel


    lateinit var LoadingUtils:LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoadingUtils= LoadingUtils(this)


        var repository = UserRepositoryImplement()
        userViewModel = UserViewModel(repository)



        binding.signUp.setOnClickListener {
            LoadingUtils.show()
            var email = binding.registerEmail.text.toString()
            var password = binding.registerPassword.text.toString()
            var firstName = binding.registerFname.text.toString()
            var lastName = binding.registerLName.text.toString()
            var address = binding.registerAddress.text.toString()
            var phone = binding.registerContact.text.toString()


            userViewModel.signup(email, password) { success, message, userId ->

                if (success) {
                    val userModel = UserModel(
                        userId.toString(),
                        firstName,
                        lastName,
                        address,
                        phone,
                        email
                    )
                    userViewModel.addUserToDatabase(userId, userModel) { success, message ->
                        if (success) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Registration Successful: $message",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {

                            Toast.makeText(
                                this@RegistrationActivity,
                                message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    LoadingUtils.dismiss()
                    Toast.makeText(
                        this@RegistrationActivity,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}







