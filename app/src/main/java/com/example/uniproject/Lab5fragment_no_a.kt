package com.example.uniproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Lab5fragment_no_a :Fragment(R.layout.fragment_lab5_show_no_a) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lab5_show_no_a, container, false)

        // Retrieve the selected text from the arguments bundle
        val selectedText = arguments?.getString("chosen item")

        val length = selectedText?.length
        val vowels = setOf('a', 'ą', 'e', 'ę', 'ė', 'i', 'į', 'y', 'o', 'u', 'ų', 'ū')
        val countVowels = selectedText?.toLowerCase()?.count{ it in vowels}
        val uppercase = selectedText?.count() {it.isUpperCase()}
        val lowercase = selectedText?.count() {it.isLowerCase()}

        val textView = view.findViewById<TextView>(R.id.textView_Lab5_with_no_a)

        textView.text = "teksto ilgis: $length \n" +
                "balsiu skaičius: $countVowels \n" +
                "didžiosios raides: $uppercase \n" +
                "mažosios raidės: $lowercase"

        return view
    }
}