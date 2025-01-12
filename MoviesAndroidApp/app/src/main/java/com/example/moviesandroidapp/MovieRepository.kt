package com.example.moviesandroidapp

import android.content.res.Resources
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository(private val resources: Resources) {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(MoviesViewModel::class.java, MoviesViewModelDeserializer(resources))
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fastapi-backend-94003400370.us-central1.run.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getMovies(callback: Callback<List<MoviesViewModel>>) {
        val call = apiService.getMovies()
        call.enqueue(callback)
    }
}