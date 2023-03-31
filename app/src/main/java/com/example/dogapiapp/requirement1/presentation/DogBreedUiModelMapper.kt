package com.example.dogapiapp.requirement1.presentation

import androidx.paging.PagingData
import androidx.paging.map
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.requirement1.model.DogBreedUiModel

fun DogBreedDbModel.toDogBreedUiModel() =
    DogBreedUiModel(
        id = breedId,
        name = name,
        imageUrl = image?.url,
    )

fun PagingData<DogBreedDbModel>.toPagingDataUiModel() =
    map {
        it.toDogBreedUiModel()
    }