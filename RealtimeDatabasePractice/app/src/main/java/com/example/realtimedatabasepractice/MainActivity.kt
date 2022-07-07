package com.example.realtimedatabasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasepractice.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // データベースのデータの読み書きに必要
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regButton.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()
            val userName = binding.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("User")
            val User = User(firstName, lastName, email, userName)
            // 主キー
            database.child("userName").child(userName).setValue(User).addOnSuccessListener {
                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.email.text.clear()
                binding.userName.text.clear()

                Toast.makeText(this, "Successfully Saved!", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchButton.setOnClickListener {
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)
        }
    }
}