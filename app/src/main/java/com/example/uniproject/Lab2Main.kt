package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniproject.databinding.ActivityLab2MainBinding
import com.example.uniproject.databinding.ActivityMainBinding

class Lab2Main : AppCompatActivity() {
    private lateinit var binding: ActivityLab2MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recievedText = intent.getStringExtra("writtenText")
        if (recievedText != null){
            binding.textViewMainText.text = recievedText
        }

        binding.buttonWriteText.setOnClickListener {
            val intent = Intent(this, Lab2writeText::class.java)
            startActivity(intent)
        }
        binding.buttonWordsCount.setOnClickListener {
            val intent = Intent(this, Lab2wordsCount::class.java)
            intent.putExtra("textForWordCounting", recievedText)
            startActivity(intent)
        }
        binding.buttonSendText.setOnClickListener {
            val intent = Intent(this, Lab2SendText::class.java)
            intent.putExtra("textToSend", recievedText)
            startActivity(intent)
        }
        binding.buttonGoToLab3.setOnClickListener {
            val intent = Intent(this, Lab3main::class.java)
            startActivity(intent)
        }
    }
}