package com.trivago.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val next: String? = null,
    val previous: String? = null,
    val results: List<CharacterResponse>
)

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val name: String,
    val height: String,
    val mass: String,
    @Json(name = "hair_color") val hairColor: String,
    @Json(name = "skin_color") val skinColor: String,
    @Json(name = "eye_color") val eyeColor: String,
    @Json(name = "birth_year") val birthYear: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>
)