package com.trivago.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeWorldResponse(
    val name: String,
    val population: String
)
