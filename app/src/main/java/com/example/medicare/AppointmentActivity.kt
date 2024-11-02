package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AppointmentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnBook: ImageButton
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        // Initialize EditTexts and Button
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        btnBook = findViewById(R.id.btnBook)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Set click listener on the book button
        btnBook.setOnClickListener {
            saveDataToFirestore()
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

    private fun saveDataToFirestore() {
        // Get text from EditTexts
        val name = etName.text.toString().trim()
        val address = etAddress.text.toString().trim()

        // Check if fields are not empty
        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a data object to store
        val appointment = hashMapOf(
            "name" to name,
            "address" to address
        )

        // Save data to Firestore
        firestore.collection("appointments")
            .add(appointment)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Show success message
                    Toast.makeText(this, "Appointment booked successfully", Toast.LENGTH_SHORT).show()

                    // Move to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Show failure message
                    Toast.makeText(this, "Failed to book appointment", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
