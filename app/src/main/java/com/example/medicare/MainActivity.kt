package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi ImageView untuk menampilkan GIF
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Memuat GIF menggunakan Glide
        Glide.with(this)
            .asGif()
            .load(R.drawable.gif)  // Ubah dengan nama file GIF Anda
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        // Tombol Profile
        findViewById<ImageButton>(R.id.btnProfile).setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }

        // Tombol Menu
        findViewById<ImageButton>(R.id.btnMenu).setOnClickListener {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Tombol Emergency
        findViewById<ImageButton>(R.id.btnEmergency).setOnClickListener {
            Toast.makeText(this, "Emergency clicked", Toast.LENGTH_SHORT).show()

            // Intent untuk berpindah ke EmergencyActivity
            val intent = Intent(this, EmergencyActivity::class.java)
            startActivity(intent)
        }


        // Tombol News
        findViewById<ImageButton>(R.id.btnNews).setOnClickListener {
            Toast.makeText(this, "Doctor clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
        }

        // Tombol Konsultasi
        findViewById<ImageButton>(R.id.btnKonsultasi).setOnClickListener {
            Toast.makeText(this, "Consultation clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ConsultationActivity::class.java)
            startActivity(intent)
        }

        // Tombol Reservasi
        findViewById<ImageButton>(R.id.btnReservasi).setOnClickListener {
            Toast.makeText(this, "Reservation clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }

        // Tombol About
        findViewById<ImageButton>(R.id.btnAbout).setOnClickListener {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Tombol Developer
        findViewById<ImageButton>(R.id.btnDeveloper).setOnClickListener {
            Toast.makeText(this, "Developer clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation Buttons
        findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.newsButton).setOnClickListener {
            Toast.makeText(this, "Doctor clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.chatButton).setOnClickListener {
            Toast.makeText(this, "Chat clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.profileButton).setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }
    }
}
