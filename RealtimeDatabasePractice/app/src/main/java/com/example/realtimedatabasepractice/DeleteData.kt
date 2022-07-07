package com.example.realtimedatabasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasepractice.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteData : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDataBinding
    // データベースのデータの読み書きに必要
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.delButton.setOnClickListener {
            var userName = binding.etUserName.text.toString()

            if(userName.isNotEmpty())
                deleteData(userName)
            else
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)
        }
    }

    private fun deleteData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child("userName").child(userName).removeValue().addOnSuccessListener {
            binding.etUserName.text.clear()

            Toast.makeText(this, "Successfully Deleted!", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}