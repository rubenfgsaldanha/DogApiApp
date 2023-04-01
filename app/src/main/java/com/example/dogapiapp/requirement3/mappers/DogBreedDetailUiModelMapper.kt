package com.example.dogapiapp.requirement3.mappers

import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel

fun DogBreedDbModel.toDogBreedDetailUiModel() =
    DogBreedDetailUiModel(
        breedName = name,
        breedGroup = breedGroup,
        origin = origin,
        temperament = temperament,
    )