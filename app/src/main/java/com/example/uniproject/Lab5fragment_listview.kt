package com.example.uniproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment

class Lab5fragment_listview :Fragment(R.layout.fragment_lab5_showlistview){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lab5_showlistview, container, false)
        val listView = view.findViewById<ListView>(R.id.ListView_lab5text)
        val Lab5randomText = resources.getStringArray((R.array.Lab5randomText))
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, Lab5randomText)
        listView.adapter = adapter


        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = Lab5randomText[position]

            // Check if the selected text contains the specific letter
            val containsLetter = selectedItem.contains('a', ignoreCase = true) //a or A

            // Create the appropriate fragment based on the condition
            val fragment = if (containsLetter) {
                Lab5fragment_a()
            } else {
                Lab5fragment_no_a()
            }

            // Pass the selected text to the fragment
            val bundle = Bundle()
            bundle.putString("chosen item", selectedItem)
            fragment.arguments = bundle

            // Replace the current fragment with the appropriate one
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_showInfo, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

}