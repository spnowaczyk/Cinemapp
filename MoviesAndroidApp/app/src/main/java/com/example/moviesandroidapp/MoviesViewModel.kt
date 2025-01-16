package com.example.moviesandroidapp

data class MoviesViewModel(
    val id: Int,
    val name: String,
    val image: String,
    val image_width: Int,
    val image_length: Int,
    val description: String,
    val rating: Int,
    val release_date: String,
    val duration: String,
    val age_restrictions: Int
)
