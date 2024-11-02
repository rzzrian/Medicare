package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EmergencyActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etAccident: EditText
    private lateinit var btnSubmit: ImageButton

    // Menggunakan Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency) // Pastikan nama layout benar

        // Inisialisasi EditText dan Button
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etAccident = findViewById(R.id.etAccident)
        btnSubmit = findViewById(R.id.btnSubmit)
        // Profile Button
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
        // Menambahkan aksi untuk tombol submit
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val accident = etAccident.text.toString().trim()

            if (name.isNotEmpty() && address.isNotEmpty() && phoneNumber.isNotEmpty() && accident.isNotEmpty()) {
                // Membuat objek data
                val data = mapOf(
                    "name" to name,
                    "address" to address,
                    "phoneNumber" to phoneNumber,
                    "accident" to accident
                )

                // Menyimpan data ke Firestore
                db.collection("accidents")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data berhasil dikirim ke Firestore", Toast.LENGTH_SHORT).show()
                        clearFields()

                        // Intent ke MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Opsional: tutup EmergencyActivity agar tidak kembali ke sini
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Gagal mengirim data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Harap isi semua bidang", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        etName.text.clear()
        etAddress.text.clear()
        etPhoneNumber.text.clear()
        etAccident.text.clear()
    }
}
