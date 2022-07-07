package com.example.realtimedatabasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasepractice.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    // データベースのデータの読み書きに必要
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.upButton.setOnClickListener {
            val userName = binding.userName.text.toString()
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()

            updateDate(userName, firstName, lastName, email)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)
        }
    }

    private fun updateDate(userName: String, firstName: String, lastName: String, email: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        val user = mapOf<String,String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email
        )

        database.child("userName").child(userName).updateChildren(user).addOnSuccessListener {
            binding.userName.text.clear()
            binding.firstName.text.clear()
            binding.lastName.text.clear()
            binding.email.text.clear()

            Toast.makeText(this, "Successfully Update!", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }
    }
}