package com.example.dogapiapp.data.remote.dto

import com.squareup.moshi.Json

data class DogBreedDto(
    @Json(name = "bre_for") val bredFor: String,
    @Json(name = "breed_group") val breedGroup: String,
    @Json(name = "height") val heightDto: HeightDto,
    @Json(name = "weight") val id: Int,
    @Json(name = "image") val imageDto: ImageDto,
    @Json(name = "life_span") val lifeSpan: String,
    @Json(name = "name") val name: String,
    @Json(name = "origin") val origin: String,
    @Json(name = "reference_image_id") val referenceImageId: String,
    @Json(name = "temperament") val temperament: String,
    @Json(name = "weight") val weightDto: WeightDto,
)