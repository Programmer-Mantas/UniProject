package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniproject.databinding.ActivityLab2SendTextBinding
import com.example.uniproject.databinding.ActivityLab2writeTextBinding

class Lab2SendText : AppCompatActivity() {
    private lateinit var binding: ActivityLab2SendTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab2SendTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recievedText = intent.getStringExtra("textToSend")
        val sendIntent: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT , recievedText)
            type = "text/plain"
        }




        val shareIntent = Intent.createChooser(sendIntent,null)
       startActivity(shareIntent)


    }
}