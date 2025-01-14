package com.example.moviesandroidapp

data class CinemasViewModel(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Int,
    val imageUrl: String,
    val image_width: Int,
    val image_length: Int
)
