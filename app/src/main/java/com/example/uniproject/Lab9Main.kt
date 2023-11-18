package com.example.uniproject

import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uniproject.databinding.ActivityLab9MainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.Locale


class Lab9Main : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLab9MainBinding
    private var mGoogleMap: GoogleMap? = null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab9MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var placeList = mutableListOf<Place>()

        binding.buttonAddAddress.setOnClickListener {
            val name = "user_input"
            val address = binding.textViewInputAddress.text.toString()
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)
            val addressInfo = addresses?.firstOrNull()

            if (addressInfo != null) {
                val longitude = addressInfo.longitude
                val latitude = addressInfo.latitude

                val newLocation = LatLng(latitude, longitude)

                // Check if googleMap is not null before moving the camera
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 16f))

                val newPlace = Place(name, latitude, longitude, address)
                val db = Firebase.firestore
                val placesCollection = db.collection("places")
                placesCollection.add(newPlace)
                    .addOnSuccessListener {
                        val updatedPlaceList = placeList.toMutableList()
                        updatedPlaceList.add(newPlace)
                        placeList = updatedPlaceList

                        // Add the marker to the GoogleMap
                        val marker = googleMap?.addMarker(
                            MarkerOptions()
                                .position(LatLng((newPlace.latitude ?: 0.0) as Double,
                                    (newPlace.longitude ?: 0.0) as Double
                                ))
                                .title(newPlace.name)
                                .snippet(newPlace.address)
                        )
                    }

            } else {
                Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
            }
        }

        // Retrieve data from Firestore
        val db = Firebase.firestore
        db.collection("places").get().addOnSuccessListener { documents ->
            for (document in documents) {
                val name = document["name"]?.toString()
                val latitude = document["latitude"] as? Double
                val longitude = document["longitude"] as? Double
                val address = document["address"]?.toString() ?: ""
                val place = Place(name = name, latitude = latitude, longitude = longitude, address = address)
                placeList += place
            }

            if (placeList.isNotEmpty()) {
                // Get the map fragment
                val mapFragment =
                    supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment

                // If the map fragment is not null, get the GoogleMap object asynchronously
                mapFragment?.getMapAsync { googleMap ->
                    // Set the global variable to the GoogleMap instance
                    this.googleMap = googleMap

                    // Iterate through the list of places and add markers to the map
                    for (place in placeList) {
                        val latitude = place.latitude?.toDouble() ?: 0.0
                        val longitude = place.longitude?.toDouble() ?: 0.0

                        val marker = googleMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(latitude, longitude))
                                .title(place.name)
                                .snippet(place.address)
                        )

                        // Optionally, move the camera to the first marker
                        if (placeList.isNotEmpty()) {
                            val firstPlace = placeList.first()
                            val firstMarker =
                                LatLng(
                                    firstPlace.latitude?.toDouble() ?: 0.0,
                                    firstPlace.longitude?.toDouble() ?: 0.0
                                )
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstMarker, 16f))
                        }
                    }
                }
            } else {
                Toast.makeText(this, "The list is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
    }
}
