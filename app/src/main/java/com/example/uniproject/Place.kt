package com.example.uniproject

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Place(
    val name: String? = null,
    val latitude: Number? = null,
    val longitude: Number? = null,
    val address: String? = null

)