package com.example.moviesandroidapp

import android.content.res.Resources
import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MoviesViewModelDeserializer(private val resources: Resources) : JsonDeserializer<MoviesViewModel> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): MoviesViewModel {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val title = jsonObject.get("title").asString
        val imageUrl = jsonObject.get("image_url").asString
        val imageWidth = jsonObject.get("image_width").asInt
        val imageLength = jsonObject.get("image_length").asInt
        val description = jsonObject.get("description").asString
        val rating = jsonObject.get("rating").asInt
        val releaseDate = jsonObject.get("release_date").asString
        val duration = jsonObject.get("duration").asString
        val ageRestrictions = jsonObject.get("age_restrictions").asInt

        return MoviesViewModel(
            id,
            title,
            imageUrl,
            imageWidth,
            imageLength,
            description,
            rating,
            releaseDate,
            duration,
            ageRestrictions
        )
    }

}


