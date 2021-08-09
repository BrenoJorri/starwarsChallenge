package com.trivago.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpeciesResponse(
    val name: String,
    val language: String
)
