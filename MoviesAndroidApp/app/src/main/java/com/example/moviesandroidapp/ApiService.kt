package com.example.moviesandroidapp

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


}
