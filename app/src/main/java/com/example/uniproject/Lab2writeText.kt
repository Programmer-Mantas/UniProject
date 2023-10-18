package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniproject.databinding.ActivityLab2MainBinding
import com.example.uniproject.databinding.ActivityLab2writeTextBinding

class Lab2writeText : AppCompatActivity() {
    private lateinit var binding: ActivityLab2writeTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab2writeTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddWrittenText.setOnClickListener {
            val text2 = binding.plainTextAddText.text.toString()
            val intent = Intent(this, Lab2Main::class.java)
            intent.putExtra("writtenText", text2)
            startActivity(intent)
        }

    }
}