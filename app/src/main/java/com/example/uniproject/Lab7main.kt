package com.example.uniproject

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.uniproject.databinding.ActivityLab7mainBinding

class Lab7main : AppCompatActivity() {

    private lateinit var binding: ActivityLab7mainBinding
    private lateinit var batteryManager: BatteryManager
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var batteryStatusTextView: TextView
    private lateinit var toggleBatteryTrackingButton: ToggleButton

    private val CHANNEL_ID = "BatteryTrackerChannel"
    private val NOTIFICATION_ID = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab7mainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        notificationManager = NotificationManagerCompat.from(this)

        batteryStatusTextView = findViewById(R.id.batteryStatusTextView)
        toggleBatteryTrackingButton = findViewById(R.id.toggleBatteryTrackingButton)

        createNotificationChannel()

        toggleBatteryTrackingButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                registerBatteryReceiver()
            } else {
                unregisterBatteryReceiver()
            }
        }

        binding.buttonGotoLab8.setOnClickListener {
            val intent = Intent(this,Lab9Main::class.java)
            startActivity(intent)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Battery Tracker Channel"
            val descriptionText = "Battery status notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun registerBatteryReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = registerReceiver(null, filter)
        val batteryLevel = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val batteryScale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPercentage = (batteryLevel?.toFloat() ?: 0f) / (batteryScale?.toFloat() ?: 1f) * 100

        batteryStatusTextView.text = "Battery Level: ${batteryPercentage.toInt()}%"

        if (batteryPercentage < 15) {
            showLowBatteryNotification()
        }
    }

    private fun showLowBatteryNotification() {
        val intent = Intent(this, Lab7main::class.java) // Replace MainActivity with your actual activity class
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_battery_low)
            .setContentTitle("Low Battery")
            .setContentText("Your battery is below 15%.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent) // Set the PendingIntent here

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }


    private fun unregisterBatteryReceiver() {
        batteryStatusTextView.text = ""
        notificationManager.cancel(NOTIFICATION_ID)
        try {
            unregisterReceiver(null)
        } catch (e: IllegalArgumentException) {
            // Receiver not registered, ignore
        }
    }

}