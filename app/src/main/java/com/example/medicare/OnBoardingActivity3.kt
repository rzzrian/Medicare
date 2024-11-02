package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class OnBoardingActivity3 : AppCompatActivity() {

    private lateinit var getStartedButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_3) // Pastikan ini nama layout yang benar

        // Inisialisasi ImageView
        getStartedButton = findViewById(R.id.get_started)

        // Listener untuk gambar Get Started
        getStartedButton.setOnClickListener {
            val intent = Intent(this, WaitActivity::class.java) // Pastikan WaitActivity terdaftar di manifest
            startActivity(intent)
            finish() // Menutup OnboardingActivity3 agar tidak kembali ketika tombol back ditekan
        }
    }
}
