package com.example.dogapiapp.requirement1.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.requirement1.model.DogBreedUiModel

fun DogBreedDbModel.toDogBreedUiModel() =
    DogBreedUiModel(
        id = id,
        name = name,
        imageUrl = image?.url,
        sortOrder = sortOrder,
    )

fun PagingData<DogBreedDbModel>.toPagingDataUiModel() =
    map {
        it.toDogBreedUiModel()
    }