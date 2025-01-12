package com.example.moviesandroidapp

import android.content.res.Resources
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class CinemasViewModelDeserializer(private val resources: Resources) : JsonDeserializer<CinemasViewModel> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: com.google.gson.JsonDeserializationContext?): CinemasViewModel {
        val jsonObject = json?.asJsonObject

        val name = jsonObject?.get("name")?.asString ?: "Unknown"
        val latitude = jsonObject?.get("latitude")?.asDouble ?: 0.0
        val longitude = jsonObject?.get("longitude")?.asDouble ?: 0.0
        val distance = jsonObject?.get("distance")?.asInt ?: 0

        return CinemasViewModel(name, latitude, longitude, distance)
    }
}