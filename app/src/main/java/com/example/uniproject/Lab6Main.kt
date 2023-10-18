package com.example.uniproject

import android.app.TimePickerDialog
import android.content.Intent
import java.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.uniproject.databinding.ActivityLab6MainBinding
import android.os.Handler
import android.widget.TextView
import android.widget.Toast


class Lab6Main : AppCompatActivity() {
    lateinit var binding: ActivityLab6MainBinding
    private var currentIndex = 0
    private var handler: Handler? = null
    private var clickedTextView: TextView? = null
    private var textToDisplay: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab6MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        //long press on textview
        registerForContextMenu(binding.textViewLab61)
        registerForContextMenu(binding.textViewLab62)



        binding.buttonGotoLab7.setOnClickListener {
            val intent = Intent(this,Lab7main::class.java)
            startActivity(intent)
        }

    }
    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hourOfDay = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
                val hourDifference = Math.abs(selectedHour - currentHour)
                var minuteDifference = Math.abs(selectedMinute - currentMinute)
                minuteDifference += hourDifference * 60


                binding.textViewLab61.text = "Skirtumas tarp dabar ir nurodyto laiko yra $minuteDifference"

                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Time Difference")
                    .setMessage("Skirtumas tarp dabar ir nurodyto laiko yra $minuteDifference")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            },
            hourOfDay,
            minute,
            true
        )
        timePickerDialog.show()
    }

    //toolbar meniu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.meniu_lab6_toolbarmeniu , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_choose_time -> {
                showTimePickerDialog()
                return true
            }
            R.id.action_shutdown -> {
                finishAffinity()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //main meniu

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.textView_lab6_1 || v?.id == R.id.textView_lab6_2) {
            clickedTextView = v as TextView // Store a reference to the clicked TextView
            menuInflater.inflate(R.menu.meniu_lab6_contextmeniu, menu) // Inflate the context menu layout
        }
    }


    private val characterRunnable = object : Runnable {
        override fun run() {
            if (currentIndex < textToDisplay?.length ?: 0) {
                val currentCharacter = textToDisplay?.get(currentIndex)
                binding.textViewLab6Povienaraide.text = currentCharacter.toString()
                currentIndex++
                handler?.postDelayed(this, 1000) // Delay for 1 second before displaying the next character
            } else {
                handler?.removeCallbacks(this) // Stop the runnable when all characters are displayed
            }
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.context_menu_item_1 -> {
                val text = binding.textViewLab61.text.toString()
                val charcount = text.replace(" ", "").length
                binding.textViewLab62.text = "Tekste yra: $charcount simboliu"

                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Simbolių skaičius")
                    .setMessage("Tekste yra: $charcount simboliu")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()

                return true
            }
            R.id.context_menu_item_2 -> {
                currentIndex = 0 // Reset the index if needed
                val text = clickedTextView?.text.toString()
                textToDisplay = text
                handler = Handler()
                handler?.post(characterRunnable)
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


}