package com.example.uniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniproject.databinding.ActivityLab5MainBinding

class Lab5Main : AppCompatActivity() {
    lateinit var binding: ActivityLab5MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab5MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTraction = supportFragmentManager.beginTransaction()
        fragmentTraction.replace(R.id.fragment_listView,Lab5fragment_listview())
        fragmentTraction.commit()

        binding.buttonGoToLab6.setOnClickListener {
            val intent = Intent(this, Lab6Main::class.java)
            startActivity(intent)
        }

    }

}