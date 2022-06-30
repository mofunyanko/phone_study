package com.example.firestorepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firestorepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // bindingクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // レイアウトを読み込む
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}