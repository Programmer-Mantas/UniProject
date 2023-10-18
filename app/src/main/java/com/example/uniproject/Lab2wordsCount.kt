package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uniproject.databinding.ActivityLab2wordsCountBinding
import com.example.uniproject.databinding.ActivityLab2writeTextBinding

class Lab2wordsCount : AppCompatActivity() {
    private lateinit var binding: ActivityLab2wordsCountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab2wordsCountBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var recievedTextToCount = intent.getStringExtra("textForWordCounting")
        if (recievedTextToCount != null){
            val workCount = countWords(recievedTextToCount)
            recievedTextToCount = "Sakinyje '$recievedTextToCount'esti $workCount žodžiai"
            binding.textViewShowWordCount.text = recievedTextToCount

            binding.buttonGoBack.setOnClickListener {
                val intent = Intent(this, Lab2Main::class.java)
                intent.putExtra("writtenText", recievedTextToCount)
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this,"Nėra įvestų žodžių", Toast.LENGTH_LONG).show()
            binding.buttonGoBack.setOnClickListener {
                val intent = Intent(this, Lab2writeText::class.java)
                startActivity(intent)
            }
        }
    }
    fun countWords(inputString: String): Int {
        val words = inputString.split("\\s+".toRegex())
        val nonEmptyWords = words.filter { it.isNotBlank() }
        return nonEmptyWords.size
    }
}



