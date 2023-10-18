package com.example.uniproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Lab5fragment_a : Fragment(R.layout.fragment_lab5_show_a) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lab5_show_a, container, false)

        // Retrieve the selected text from the arguments bundle
        val selectedText = arguments?.getString("chosen item")


        val count = selectedText?.count{ it.equals('a',ignoreCase = true)}


        // Now you can use the selectedText as needed in your fragment
        // For example, you can display it in a TextView
        val textView = view.findViewById<TextView>(R.id.textView_Lab5_with_a)

        textView.text = "tekste '$selectedText' yra $count a "

        return view
    }
}