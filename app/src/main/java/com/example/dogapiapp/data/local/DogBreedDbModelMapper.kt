package com.example.dogapiapp.data.local

import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.model.HeightDbModel
import com.example.dogapiapp.data.local.model.ImageDbModel
import com.example.dogapiapp.data.local.model.WeightDbModel
import com.example.dogapiapp.data.remote.dto.DogBreedDto
import com.example.dogapiapp.data.remote.dto.HeightDto
import com.example.dogapiapp.data.remote.dto.ImageDto
import com.example.dogapiapp.data.remote.dto.WeightDto

fun List<DogBreedDto>.toDogBreedDbModelList() =
    map {
        it.toDogBreedDbModel()
    }

fun DogBreedDto.toDogBreedDbModel() =
    DogBreedDbModel(
        bredFor = bredFor,
        breedGroup = breedGroup,
        height = height?.toHeightDbModel(),
        breedId = id,
        image = image?.toImageDbModel(),
        lifeSpan = lifeSpan,
        name = name,
        origin = origin,
        referenceImageId = referenceImageId,
        temperament = temperament,
        weight = weight?.toWeightDbModel(),
    )

fun HeightDto.toHeightDbModel() =
    HeightDbModel(
        imperial = imperial,
        metric = metric,
    )

fun WeightDto.toWeightDbModel() =
    WeightDbModel(
        imperial = imperial,
        metric = metric,
    )

fun ImageDto.toImageDbModel() =
    ImageDbModel(
        height = height,
        imageId = id,
        url = url,
        width = width,
    )
