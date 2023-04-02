package com.example.dogapiapp.requirement2.mappers

import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel

fun List<DogBreedDbModel>.toDogBreedSearchUiModelList() =
    map {
        it.toDogBreedSearchUiModel()
    }

fun DogBreedDbModel.toDogBreedSearchUiModel() =
    DogBreedSearchUiModel(
        id = id,
        breedName = name,
        breedGroup = breedGroup,
        origin = origin,
    )