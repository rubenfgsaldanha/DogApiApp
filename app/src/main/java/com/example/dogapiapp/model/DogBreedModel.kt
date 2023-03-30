package com.example.dogapiapp.model

data class DogBreedModel(
    val bredFor: String,
    val breedGroup: String,
    val height: HeightModel,
    val id: Int,
    val image: ImageModel,
    val lifeSpan: String,
    val name: String,
    val origin: String,
    val referenceImageId: String,
    val temperament: String,
    val weight: WeightModel,
)