package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ReservationActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etDate: EditText
    private lateinit var etDisease: EditText
    private lateinit var btnSubmit: ImageButton
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation) // Ganti dengan nama layout Anda

        // Inisialisasi Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Inisialisasi View
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etDate = findViewById(R.id.etDate)
        etDisease = findViewById(R.id.etDisease)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            sendDataToFirestore()
        }
        // Profile Button
        val btnProfile: ImageButton = findViewById(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }

        // Menu Button
        val btnMenu: ImageButton = findViewById(R.id.btnMenu)
        btnMenu.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }


        // Bottom Navigation Buttons
        // Home Button
        val homeButton: ImageButton = findViewById(R.id.homeButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // News Button
        val newsButton: ImageButton = findViewById(R.id.newsButton)
        newsButton.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
        }

        // Chat Button
        val chatButton: ImageButton = findViewById(R.id.chatButton)
        chatButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Profile Button on Bottom Navigation
        val profileButton: ImageButton = findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendDataToFirestore() {
        val name = etName.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val date = etDate.text.toString().trim()
        val disease = etDisease.text.toString().trim()

        // Validasi input
        if (name.isEmpty() || address.isEmpty() || date.isEmpty() || disease.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Buat objek user untuk data yang akan disimpan
        val userData = hashMapOf(
            "name" to name,
            "address" to address,
            "date" to date,
            "disease" to disease
        )

        // Kirim data ke Firestore di koleksi "users"
        firestore.collection("users")
            .add(userData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Data sent successfully", Toast.LENGTH_SHORT).show()
                    // Arahkan ke MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Opsional: Tutup Activity saat ini
                } else {
                    Toast.makeText(this, "Failed to send data", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
