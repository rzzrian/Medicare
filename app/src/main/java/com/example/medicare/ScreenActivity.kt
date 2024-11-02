package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        // Ambil referensi ke ImageView
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Muat animasi dari file XML
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // Setel AnimationListener untuk mendeteksi akhir animasi
        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Tidak perlu melakukan apa-apa di sini
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Pindah ke LoginActivity setelah animasi berakhir
                val intent = Intent(this@ScreenActivity, OnBoardingActivity1::class.java)
                startActivity(intent)
                finish() // Tutup MainActivity agar tidak kembali saat tombol "Back" ditekan
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Tidak perlu melakukan apa-apa di sini
            }
        })

        // Jalankan animasi pada ImageView
        imageView.startAnimation(fadeInAnimation)
    }
}
