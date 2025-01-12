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

        val imageUrl = jsonObject.get("image_url").asString
        val title = jsonObject.get("title").asString
        val description = jsonObject.get("description").asString

        return MoviesViewModel(imageUrl, title, description)
    }
}


