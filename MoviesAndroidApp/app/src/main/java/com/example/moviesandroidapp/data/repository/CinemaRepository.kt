package com.example.moviesandroidapp.data.repository

import android.content.res.Resources
import com.example.moviesandroidapp.api.ApiService
import com.example.moviesandroidapp.data.model.CinemasViewModelDeserializer
import com.example.moviesandroidapp.ui.cinema.CinemasViewModel
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CinemaRepository(private val resources: Resources) {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(CinemasViewModel::class.java, CinemasViewModelDeserializer(resources))
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fastapi-backend-94003400370.us-central1.run.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getCinemas(latitude: Double, longitude: Double, maxDistance: Int, callback: Callback<List<CinemasViewModel>>) {
        val call = apiService.getCinemas(
            userLatitude = latitude,
            userLongitude = longitude,
            maxDistance = maxDistance
        )
        call.enqueue(callback)
    }
}

