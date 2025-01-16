package com.example.moviesandroidapp.ui.cinema

data class CinemasViewModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val image_width: Int,
    val image_length: Int,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val address: String,
    val postal_code: String,
    val country: String,
    val distance: Int
)
