package com.example.dogapiapp.data.remote.remotedatasource

import com.example.dogapiapp.data.remote.dto.DogBreedDto
import com.example.dogapiapp.data.remote.dto.HeightDto
import com.example.dogapiapp.data.remote.dto.ImageDto
import com.example.dogapiapp.data.remote.dto.WeightDto
import com.example.dogapiapp.model.DogBreedModel
import com.example.dogapiapp.model.HeightModel
import com.example.dogapiapp.model.ImageModel
import com.example.dogapiapp.model.WeightModel

fun List<DogBreedDto>.toDogBreedModel() =
    map {
        DogBreedModel(
            bredFor = it.bredFor,
            breedGroup = it.breedGroup,
            height = it.heightDto.toHeightModel(),
            id = it.id,
            image = it.imageDto.toImageModel(),
            lifeSpan = it.lifeSpan,
            name = it.name,
            origin = it.origin,
            referenceImageId = it.referenceImageId,
            temperament = it.temperament,
            weight = it.weightDto.toWeightModel(),
        )
    }

fun HeightDto.toHeightModel() =
    HeightModel(
        imperial = imperial,
        metric = metric,
    )

fun WeightDto.toWeightModel() =
    WeightModel(
        imperial = imperial,
        metric = metric,
    )

fun ImageDto.toImageModel() =
    ImageModel(
        height = height,
        id = id,
        url = url,
        width = width,
    )