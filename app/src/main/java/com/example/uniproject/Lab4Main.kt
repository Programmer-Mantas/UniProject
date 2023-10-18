package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.uniproject.databinding.ActivityLab3mainBinding
import com.example.uniproject.databinding.ActivityLab4MainBinding

class Lab4Main : AppCompatActivity() {
    private lateinit var binding: ActivityLab4MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab4MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSearch.setOnClickListener {
            val url = binding.editTextTinklapis.text.toString()
            val settings = binding.webViewShowPage.settings
            settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
            settings.javaScriptEnabled = true
            binding.webViewShowPage.webViewClient = WebViewClient()
            binding.webViewShowPage.loadUrl(url)

        }
        binding.buttonGotoLab5.setOnClickListener {
            val intent = Intent(this, Lab5Main::class.java)
            startActivity(intent)
        }
    }
}