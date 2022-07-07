package com.example.realtimedatabasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasepractice.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {
    private lateinit var binding: ActivityReadDataBinding
    // データベースのデータの読み書きに必要
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readButton.setOnClickListener {
            val userName : String = binding.etUserName.text.toString()
            if (userName.isNotEmpty()) {
                readData(userName)
            }else {
                Toast.makeText(this, "Please enter the Username", Toast.LENGTH_SHORT).show()
            }
        }

        binding.upButton.setOnClickListener {
            val intent = Intent(this, UpdateData::class.java)
            startActivity(intent)
        }

        binding.delButton.setOnClickListener {
            val intent = Intent(this, DeleteData::class.java)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child("userName").child(userName).get().addOnSuccessListener {
            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val email = it.child("email").value

                Toast.makeText(this, "Successfully Read!", Toast.LENGTH_SHORT).show()

                binding.etUserName.text.clear()
                binding.tvFirstName.text = firstName.toString()
                binding.tvLastName.text = lastName.toString()
                binding.tvEmail.text = email.toString()
            }else {
                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}