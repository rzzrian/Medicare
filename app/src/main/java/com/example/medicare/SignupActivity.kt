package com.example.medicare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SignupActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail) // Input email
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignup = findViewById<ImageButton>(R.id.btnSignup)
        val tvAlreadyHaveAccount = findViewById<TextView>(R.id.tvAlreadyHaveAccount)

        // Buat teks "Already have an account? Login" dapat diklik
        val spannableString = SpannableString("Already have an account? Login")
        val loginTextColorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue))
        spannableString.setSpan(loginTextColorSpan, 25, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Buat "Login" dapat diklik
        val loginClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(this@SignupActivity, R.color.blue)
            }
        }
        spannableString.setSpan(loginClickableSpan, 25, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvAlreadyHaveAccount.text = spannableString
        tvAlreadyHaveAccount.movementMethod = LinkMovementMethod.getInstance()

        btnSignup.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim() // Ambil input email
            val password = etPassword.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Periksa apakah pengguna sudah terdaftar
                val registeredUsers = sharedPreferences.getStringSet("registered_users", mutableSetOf()) ?: mutableSetOf()
                if (registeredUsers.contains(username)) {
                    // Pengguna sudah terdaftar, navigasikan ke LoginActivity
                    Toast.makeText(this, "Username sudah terdaftar. Silakan login.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                } else {
                    // Lanjutkan untuk mendaftar pengguna
                    registerUser(username, email, password) // Daftar dengan email
                    Toast.makeText(this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                    // Navigasi ke LoginActivity setelah berhasil mendaftar
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        // Simpan informasi pengguna di SharedPreferences
        val editor = sharedPreferences.edit()

        // Simpan username, email, dan password
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)

        // Simpan username dalam set untuk memeriksa pengguna yang sudah terdaftar
        val registeredUsers = sharedPreferences.getStringSet("registered_users", mutableSetOf()) ?: mutableSetOf()
        registeredUsers.add(username) // Tambahkan username ke dalam set
        editor.putStringSet("registered_users", registeredUsers)

        editor.apply()
    }
}
