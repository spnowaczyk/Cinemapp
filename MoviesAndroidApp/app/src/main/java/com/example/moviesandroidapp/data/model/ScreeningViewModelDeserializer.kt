package com.example.moviesandroidapp.data.model

import android.content.res.Resources
import com.example.moviesandroidapp.ui.movie.MoviesViewModel
import com.example.moviesandroidapp.ui.screening.ScreeningViewModel
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ScreeningViewModelDeserializer(private val resources: Resources) :
        JsonDeserializer<ScreeningViewModel>
{
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): ScreeningViewModel
    {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val cinemaId = jsonObject.get("cinema_id").asInt
        val movieId = jsonObject.get("movie_id").asInt
        val screeningTechnologyId = jsonObject.get("screening_technology_id").asInt
        val time = if (jsonObject.has("time") && !jsonObject.get("time").isJsonNull) {
            jsonObject.get("time").asString
        } else {
            "Unknown"
        }

        val audioLang = jsonObject.get("audio_lang").asString
        val subtitlesLang = if (jsonObject.has("subtitles_lang") && !jsonObject.get("subtitles_lang").isJsonNull) {
            jsonObject.get("subtitles_lang").asString
        } else {
            null
        }
        val specialNotes = if (jsonObject.has("special_notes") && !jsonObject.get("special_notes").isJsonNull) {
            jsonObject.get("special_notes").asString
        } else {
            null
        }

        return ScreeningViewModel(
            id,
            cinemaId,
            movieId,
            screeningTechnologyId,
            time,
            audioLang,
            subtitlesLang,
            specialNotes
        )
    }
}