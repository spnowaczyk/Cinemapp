package com.example.moviesandroidapp.data.model

import android.content.res.Resources
import com.example.moviesandroidapp.ui.screening.ScreeningViewModel
import com.example.moviesandroidapp.ui.trailer.TrailerViewModel
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TrailerViewModelDeserializer(private val resources: Resources) :
    JsonDeserializer<TrailerViewModel>
{
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): TrailerViewModel
    {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val movieId = jsonObject.get("movie_id").asInt
        val embeddingString = jsonObject.get("embedding_string").asString


        return TrailerViewModel(
            id,
            movieId,
            embeddingString
        )
    }
}