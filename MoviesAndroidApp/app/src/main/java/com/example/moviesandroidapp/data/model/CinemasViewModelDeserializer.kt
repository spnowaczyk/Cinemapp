package com.example.moviesandroidapp.data.model

import android.content.res.Resources
import com.example.moviesandroidapp.ui.cinema.CinemasViewModel
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class CinemasViewModelDeserializer(private val resources: Resources) : JsonDeserializer<CinemasViewModel> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: com.google.gson.JsonDeserializationContext?): CinemasViewModel
    {
        val jsonObject = json?.asJsonObject

        val id = jsonObject?.get("id")?.asInt ?: 0
        val name = jsonObject?.get("name")?.asString ?: "Unknown"
        val latitude = jsonObject?.get("latitude")?.asDouble ?: 0.0
        val longitude = jsonObject?.get("longitude")?.asDouble ?: 0.0
        val distance = jsonObject?.get("distance")?.asInt ?: 0
        val imageUrl = jsonObject?.get("image_url")?.asString ?: ""
        val image_width = jsonObject?.get("image_width")?.asInt ?: 0
        val image_length = jsonObject?.get("image_length")?.asInt ?: 0
        val city = jsonObject?.get("city")?.asString ?: ""
        val address = jsonObject?.get("address")?.asString ?: ""
        val postal_code = jsonObject?.get("postal_code")?.asString ?: ""
        val country = jsonObject?.get("country")?.asString ?: ""

        return CinemasViewModel(
            id,
            name,
            imageUrl,
            image_width,
            image_length,
            latitude,
            longitude,
            city,
            address,
            postal_code,
            country,
            distance
        )

    }
}