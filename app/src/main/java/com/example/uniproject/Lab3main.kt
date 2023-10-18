package com.example.uniproject

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.uniproject.databinding.ActivityLab2MainBinding
import com.example.uniproject.databinding.ActivityLab3mainBinding

class Lab3main : AppCompatActivity() {
    private lateinit var binding: ActivityLab3mainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab3mainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /// Fakultetas
        val fakul = resources.getStringArray((R.array.Fakultetai))
        val adapterFak: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item, fakul)
        binding.autoCompleteTextFakultetas.threshold = 1
        binding.autoCompleteTextFakultetas.setAdapter(adapterFak)
        //

        /// Diena
        val adapterDiena = ArrayAdapter.createFromResource(
            this,
            R.array.SavaiteDienos,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.spinnerDiena.adapter = adapterDiena


        //

        binding.buttonSaugoti.setOnClickListener {
            val name = binding.editTextPavadinimas.text.toString()
            Toast.makeText(this,"Pavadinimas: $name", Toast.LENGTH_SHORT).show()
            val fak = binding.autoCompleteTextFakultetas.text.toString()
            Toast.makeText(this,"Pasirinktas fakultetas: $fak", Toast.LENGTH_SHORT).show()
            val stars = binding.raitingStarSudetingums.rating.toString()
            Toast.makeText(this,"Sudetingumas ivertintas: $stars", Toast.LENGTH_SHORT).show()
            val day = binding.spinnerDiena.selectedItem.toString()
            Toast.makeText(this,"Sudetingumas ivertintas: $day", Toast.LENGTH_SHORT).show()
            val hour = binding.timePickerLaikas.hour
            val minute = binding.timePickerLaikas.minute
            Toast.makeText(this,"Pasirinktas laikas: $hour val ir  $minute min", Toast.LENGTH_SHORT).show()
            val registruotis = binding.switchRegistruotis.isChecked
            if (registruotis) Toast.makeText(this,"Nori registruotis", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Nenori registruotis", Toast.LENGTH_SHORT).show()
        }

        binding.buttonGoToLab4.setOnClickListener {
            val intent = Intent(this, Lab4Main::class.java)
            startActivity(intent)
        }
    }
}