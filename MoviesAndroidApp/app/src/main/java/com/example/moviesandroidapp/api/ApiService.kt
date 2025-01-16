package com.example.moviesandroidapp.api

import com.example.moviesandroidapp.ui.cinema.CinemasViewModel
import com.example.moviesandroidapp.ui.movie.MoviesViewModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movie")
    fun getMovies(
        @Query("id") id: Int? = null,
        @Query("title") title: String? = null,
        @Query("rating") rating: Int? = null,
    ): Call<List<MoviesViewModel>>

    @GET("/cinemas")
    fun getCinemas(
        @Query("id") id: Int? = null,
        @Query("name") name: String? = null,
        @Query("city") city: String? = null,
        @Query("address") address: String? = null,
        @Query("postal_code") postalCode: String? = null,
        @Query("country") country: String? = null,
        @Query("user_latitude") userLatitude: Double? = null,
        @Query("user_longitude") userLongitude: Double? = null,
        @Query("max_distance") maxDistance: Int? = null
    ): Call<List<CinemasViewModel>>
}
