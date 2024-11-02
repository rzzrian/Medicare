package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WaitActivity : AppCompatActivity() {

    private lateinit var loginButton: ImageView
    private lateinit var signupButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait) // Pastikan nama layout sesuai

        // Inisialisasi ImageView
        loginButton = findViewById(R.id.loginbutton)
        signupButton = findViewById(R.id.signupbutton)

        // Listener untuk tombol Login
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Listener untuk tombol Signup
        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
