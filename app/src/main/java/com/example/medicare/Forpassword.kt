package com.example.medicare

import android.content.Intent
import android.graphics.Color
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
import com.google.firebase.auth.FirebaseAuth

class Forpassword : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var sendEmailButton: ImageButton
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forpasword) // Pastikan nama layout benar

        auth = FirebaseAuth.getInstance() // Inisialisasi Firebase Auth

        emailInput = findViewById(R.id.etUsername) // Menggunakan ID yang sudah ada
        sendEmailButton = findViewById(R.id.btnSendEmail)

        // Mengatur listener untuk tombol kirim email
        sendEmailButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            if (email.isNotEmpty()) {
                sendPasswordResetEmail(email)
            } else {
                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        // Inisialisasi TextViews dan ClickableSpans
        val tvAlreadyHaveAccount = findViewById<TextView>(R.id.tvAlreadyHaveAccount)
        val tvCreateOne = findViewById<TextView>(R.id.tvCreateOne)

        // Mengatur teks "Already have an account? Login"
        val alreadyHaveAccountText = SpannableString("Already have an account? Login")
        val loginStart = alreadyHaveAccountText.indexOf("Login")
        val loginEnd = loginStart + "Login".length

        val loginClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigasi ke halaman login
                startActivity(Intent(this@Forpassword, LoginActivity::class.java))
            }
        }
        alreadyHaveAccountText.setSpan(loginClickableSpan, loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        alreadyHaveAccountText.setSpan(ForegroundColorSpan(Color.BLUE), loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvAlreadyHaveAccount.text = alreadyHaveAccountText
        tvAlreadyHaveAccount.movementMethod = LinkMovementMethod.getInstance()

        // Mengatur teks "Don't have an account? Create one"
        val createOneText = SpannableString("Don't have an account? Create one")
        val createStart = createOneText.indexOf("Create one")
        val createEnd = createStart + "Create one".length

        val createAccountClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigasi ke halaman pendaftaran
                startActivity(Intent(this@Forpassword, SignupActivity::class.java))
            }
        }
        createOneText.setSpan(createAccountClickableSpan, createStart, createEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        createOneText.setSpan(ForegroundColorSpan(Color.BLUE), createStart, createEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvCreateOne.text = createOneText
        tvCreateOne.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Email pemulihan kata sandi telah dikirim", Toast.LENGTH_SHORT).show()
                // Pindah ke LoginActivity setelah mengirim email
                startActivity(Intent(this@Forpassword, LoginActivity::class.java))
                finish() // Opsional: Tutup activity saat ini
            } else {
                Toast.makeText(this, "Gagal mengirim email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
