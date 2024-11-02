package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        // Menemukan ImageButton berdasarkan ID
        val btnDoc1: ImageButton = findViewById(R.id.btnDoc1)
        val btnDoc2: ImageButton = findViewById(R.id.btnDoc2)
        val btnDoc3: ImageButton = findViewById(R.id.btnDoc3)
        val btnDoc4: ImageButton = findViewById(R.id.btnDoc4)

        // Setiap button dokter akan membuka AppointmentActivity
        btnDoc1.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }

        btnDoc2.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }

        btnDoc3.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }

        btnDoc4.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk Profile Button
        findViewById<ImageButton>(R.id.btnProfile).setOnClickListener {
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk Menu Button
        findViewById<ImageButton>(R.id.btnMenu).setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk Home Button
        findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk News Button
        findViewById<ImageButton>(R.id.newsButton).setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk Chat Button
        findViewById<ImageButton>(R.id.chatButton).setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk Profile Button di Navigation
        findViewById<ImageButton>(R.id.profileButton).setOnClickListener {
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }
    }
}