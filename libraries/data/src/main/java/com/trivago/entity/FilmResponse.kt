package com.trivago.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmResponse(
    val title: String,
    @Json(name = "opening_crawl") val description: String
)
