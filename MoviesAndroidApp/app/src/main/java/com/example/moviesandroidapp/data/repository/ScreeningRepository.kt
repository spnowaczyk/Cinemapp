package com.example.moviesandroidapp.data.repository

import android.content.res.Resources
import com.example.moviesandroidapp.api.ApiService
import com.example.moviesandroidapp.ui.screening.ScreeningViewModel
import com.example.moviesandroidapp.data.model.ScreeningViewModelDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScreeningRepository(private val resources: Resources) {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(ScreeningViewModel::class.java, ScreeningViewModelDeserializer(resources))
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fastapi-backend-94003400370.us-central1.run.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getScreenings(cinemaId: Int, movieId: Int, callback: Callback<List<ScreeningViewModel>>) {
        val call = apiService.getScreenings(cinemaId, movieId)
        call.enqueue(callback)
    }
}
