package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class OnBoardingActivity1 : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private lateinit var skip: TextView
    private lateinit var next: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_1)

        // Inisialisasi TextView dan Button
        descriptionTextView = findViewById(R.id.description)
        skip = findViewById(R.id.skip)
        next = findViewById(R.id.next)

        // Mengambil warna biru dari resources dan mengatur warna teks skip dan next
        val colorBlue = ContextCompat.getColor(this, R.color.blue)
        skip.setTextColor(colorBlue)
        next.setTextColor(colorBlue)

        // Listener untuk tombol SKIP
        skip.setOnClickListener {
            val intent = Intent(this, WaitActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Listener untuk tombol NEXT
        next.setOnClickListener {
            val intent = Intent(this, OnBoardingActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
