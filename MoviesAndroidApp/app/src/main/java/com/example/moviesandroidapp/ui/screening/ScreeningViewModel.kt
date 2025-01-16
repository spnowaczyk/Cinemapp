package com.example.moviesandroidapp.ui.screening

data class ScreeningViewModel(
    val id: Int,
    val cinema_id: Int,
    val movie_id: Int,
    val screening_technology_id: Int,
    val time: String,
    val audio_lang: String,
    val subtitles_lang: String?,
    val special_notes: String?
)