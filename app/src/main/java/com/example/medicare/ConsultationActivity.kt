package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ConsultationActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etGender: EditText
    private lateinit var etDisease: EditText
    private lateinit var btnSubmit: ImageButton
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        // Initialize EditTexts and Button
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etGender = findViewById(R.id.etGender)
        etDisease = findViewById(R.id.etDisease)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Set click listener on the submit button
        btnSubmit.setOnClickListener {
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
        val age = etAge.text.toString().trim()
        val gender = etGender.text.toString().trim()
        val disease = etDisease.text.toString().trim()

        // Check if fields are not empty
        if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || disease.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a data object to store
        val consultation = hashMapOf(
            "name" to name,
            "age" to age,
            "gender" to gender,
            "disease" to disease
        )

        // Save data to Firestore
        firestore.collection("consultations")
            .add(consultation)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Show success message
                    Toast.makeText(this, "Consultation data submitted successfully", Toast.LENGTH_SHORT).show()

                    // Move to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Show failure message
                    Toast.makeText(this, "Failed to submit consultation data", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
