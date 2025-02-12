package com.example.shreebeautystudio.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.btnSignupnavigate.setOnClickListener{
    //show
    val intent= Intent(this@LoginActivity,
        RegistrationActivity::class.java)

    startActivity(intent)
}

        binding.btnLogin.setOnClickListener{
            val intent= Intent(this@LoginActivity,
                NavigationActivty::class.java)

            startActivity(intent)
        }


        binding.btnForget.setOnClickListener{
            val intent= Intent(this@LoginActivity,
                ForgetActivity::class.java)

            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}