package com.example.moviesandroidapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesandroidapp.databinding.ActivityCinemasBinding
import com.google.android.gms.location.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CinemasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCinemasBinding
    private lateinit var cinemaRepository: CinemaRepository
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var cinemasAdapter: CinemasAdapter
    private val cinemasData = ArrayList<CinemasViewModel>()

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCinemasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        cinemaRepository = CinemaRepository(resources)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initializeCinemasView()
            fetchCinemasFromApi()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun initializeCinemasView() {
        val cinemasView = binding.cinemasView
        cinemasView.layoutManager = LinearLayoutManager(this)

        cinemasAdapter = CinemasAdapter(cinemasData)
        cinemasView.adapter = cinemasAdapter
    }

    private fun fetchCinemasFromApi() {
        getLastLocation()
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000 // 10 seconds interval
            fastestInterval = 5000 // 5 seconds for the fastest
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let { location ->
                    val userLatitude = location.latitude
                    val userLongitude = location.longitude
                    val maxDistance = 10 // 10km

                    Log.d("CinemasActivity", "Latitude: $userLatitude, Longitude: $userLongitude")

                    cinemaRepository.getCinemas(userLatitude, userLongitude, maxDistance, object : Callback<List<CinemasViewModel>> {
                        override fun onResponse(call: Call<List<CinemasViewModel>>, response: Response<List<CinemasViewModel>>) {
                            if (response.isSuccessful) {
                                val cinemas = response.body()
                                if (cinemas != null) {
                                    cinemasData.clear()

                                    cinemas.forEach { cinema ->
                                        cinema.latitude?.let { cinemaLat ->
                                            cinema.longitude?.let { cinemaLon ->
                                                val distance = calculateDistance(userLatitude, userLongitude, cinemaLat, cinemaLon)
                                                cinemasData.add(CinemasViewModel(cinema.name, cinemaLat, cinemaLon, distance.toInt(), cinema.imageUrl, cinema.image_width, cinema.image_length))
                                            }
                                        }
                                    }

                                    cinemasAdapter.notifyDataSetChanged()
                                }
                            } else {
                                Log.e("CinemasActivity", "Error fetching cinemas: ${response.message()}")
                            }
                        }

                        override fun onFailure(call: Call<List<CinemasViewModel>>, t: Throwable) {
                            Log.e("CinemasActivity", "Failed to fetch cinemas: ${t.message}")
                        }
                    })
                } ?: Log.e("CinemasActivity", "Location is null")
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371 // Earth radius in kilometers
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c * 1000// Distance in meters
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeCinemasView()
                fetchCinemasFromApi()
            } else {
                Log.e("CinemasActivity", "Location permission denied.")
            }
        }
    }
}
