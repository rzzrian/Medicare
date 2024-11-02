package com.example.medicare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE) // Inisialisasi SharedPreferences

        val usernameInput = findViewById<EditText>(R.id.etUsername) // Ambil input username
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<ImageButton>(R.id.btnLogin)
        val forgotPasswordButton = findViewById<Button>(R.id.btnForgotPassword)

        // Set listener untuk tombol login
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim() // Ambil username
            val password = passwordInput.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginUser(username, password) // Panggil loginUser dengan username
            } else {
                Toast.makeText(this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

            forgotPasswordButton.setOnClickListener {
                startActivity(Intent(this, Forpassword::class.java))
            }

        // Set teks dapat diklik untuk "Create One"
        val tvCreateOne = findViewById<TextView>(R.id.tvCreateOne)
        val spannable = SpannableString("Still Without Account? Create One!")
        val colorBlue = ContextCompat.getColor(this, R.color.blue)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
        }

        val start = spannable.indexOf("Create One!")
        val end = start + "Create One!".length
        spannable.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(colorBlue), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvCreateOne.text = spannable
        tvCreateOne.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun loginUser(username: String, password: String) {
        // Ambil informasi pengguna yang tersimpan di SharedPreferences
        val registeredUsername = sharedPreferences.getString("username", null)
        val registeredPassword = sharedPreferences.getString("password", null)

        // Periksa apakah username dan password yang dimasukkan sesuai
        if (username == registeredUsername && password == registeredPassword) {
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
        }
    }
}
