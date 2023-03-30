package com.example.dogapiapp.data.local

import com.example.dogapiapp.data.local.dbmodels.DogBreedDbModel
import com.example.dogapiapp.data.local.dbmodels.HeightDbModel
import com.example.dogapiapp.data.local.dbmodels.ImageDbModel
import com.example.dogapiapp.data.local.dbmodels.WeightDbModel
import com.example.dogapiapp.model.DogBreedModel
import com.example.dogapiapp.model.HeightModel
import com.example.dogapiapp.model.ImageModel
import com.example.dogapiapp.model.WeightModel

fun List<DogBreedModel>.toDogBreedDbModelList() =
    map {
        it.toDogBreedDbModel()
    }

fun DogBreedModel.toDogBreedDbModel() =
    DogBreedDbModel(
        bredFor = bredFor,
        breedGroup = breedGroup,
        height = height.toHeightDbModel(),
        breedId = id,
        image = image.toImageDbModel(),
        lifeSpan = lifeSpan,
        name = name,
        origin = origin,
        referenceImageId = referenceImageId,
        temperament = temperament,
        weight = weight.toWeightDbModel(),
    )

fun HeightModel.toHeightDbModel() =
    HeightDbModel(
        imperial = imperial,
        metric = metric,
    )

fun WeightModel.toWeightDbModel() =
    WeightDbModel(
        imperial = imperial,
        metric = metric,
    )

fun ImageModel.toImageDbModel() =
    ImageDbModel(
        height = height,
        imageId = id,
        url = url,
        width = width,
    )
