package com.example.dogapiapp.data.remote.dto

import com.squareup.moshi.Json

data class WeightDto(
    @Json(name = "imperial") val imperial: String,
    @Json(name = "metric") val metric: String,
)