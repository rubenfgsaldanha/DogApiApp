package com.example.dogapiapp.data.remote.dto

import com.squareup.moshi.Json

data class HeightDto(
    @Json(name = "imperial") val imperial: String,
    @Json(name = "metric") val metric: String,
)