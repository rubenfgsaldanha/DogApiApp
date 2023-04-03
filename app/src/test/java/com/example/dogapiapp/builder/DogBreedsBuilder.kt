package com.example.dogapiapp.builder

import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.model.HeightDbModel
import com.example.dogapiapp.data.local.model.ImageDbModel
import com.example.dogapiapp.data.local.model.WeightDbModel
import com.example.dogapiapp.data.remote.dto.DogBreedDto
import com.example.dogapiapp.data.remote.dto.HeightDto
import com.example.dogapiapp.data.remote.dto.ImageDto
import com.example.dogapiapp.data.remote.dto.WeightDto
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel

object DogBreedsBuilder {

    private const val BRED_FOR = "BRED_FOR"
    private const val BREED_GROUP = "BREED_GROUP"
    private const val HEIGHT_METRIC = "HEIGHT_METRIC"
    private const val HEIGHT_IMPERIAL = "HEIGHT_IMPERIAL"
    private const val ID = 1
    private const val IMAGE_HEIGHT = 1234
    private const val IMAGE_ID = "IMAGE_ID"
    private const val IMAGE_URL = "IMAGE_URL"
    private const val IMAGE_WIDTH = 1234
    private const val LIFE_SPAN = "LIFE_SPAN"
    private const val NAME = "NAME"
    private const val ORIGIN = "ORIGIN"
    private const val REFERENCE_IMAGE_ID = "REFERENCE_IMAGE_ID"
    private const val TEMPERAMENT = "TEMPERAMENT"
    private const val WEIGHT_METRIC = "WEIGHT_METRIC"
    private const val WEIGHT_IMPERIAL = "WEIGHT_IMPERIAL"
    private const val SORT_ORDER = 0

    internal fun aDogBreedDtoResponse() =
        listOf(
            DogBreedDto(
                bredFor = BRED_FOR,
                breedGroup = BREED_GROUP,
                height = aHeightDtoModel(),
                id = ID,
                image = anImageDtoModel(),
                lifeSpan = LIFE_SPAN,
                name = NAME,
                origin = ORIGIN,
                referenceImageId = REFERENCE_IMAGE_ID,
                temperament = TEMPERAMENT,
                weight = aWeightDtoModel(),
            ),
            DogBreedDto(
                bredFor = BRED_FOR,
                breedGroup = BREED_GROUP,
                height = aHeightDtoModelWithNullValues(),
                id = ID.plus(1),
                image = anImageDtoModelWithNullValues(),
                lifeSpan = LIFE_SPAN,
                name = NAME.plus("1"),
                origin = null,
                referenceImageId = null,
                temperament = null,
                weight = aWeightDtoModelWithNullValues(),
            ),
        )

    private fun aHeightDtoModel() =
        HeightDto(
            metric = HEIGHT_METRIC,
            imperial = HEIGHT_IMPERIAL,
        )

    private fun aHeightDtoModelWithNullValues() =
        HeightDto(
            metric = null,
            imperial = HEIGHT_IMPERIAL,
        )

    private fun aWeightDtoModel() =
        WeightDto(
            metric = WEIGHT_METRIC,
            imperial = WEIGHT_IMPERIAL,
        )

    private fun aWeightDtoModelWithNullValues() =
        WeightDto(
            metric = WEIGHT_METRIC,
            imperial = null,
        )

    private fun anImageDtoModel() =
        ImageDto(
            height = IMAGE_HEIGHT,
            id = IMAGE_ID,
            url = IMAGE_URL,
            width = IMAGE_WIDTH,
        )

    private fun anImageDtoModelWithNullValues() =
        ImageDto(
            height = IMAGE_HEIGHT,
            id = IMAGE_ID,
            url = null,
            width = IMAGE_WIDTH,
        )

    internal fun aDogBreedDbModelList() =
        listOf(
            aDogBreedDbModel(),
            DogBreedDbModel(
                bredFor = BRED_FOR,
                breedGroup = BREED_GROUP,
                height = aHeightDbModelWithNullValues(),
                id = ID.plus(1),
                image = anImageDbModelWithNullValues(),
                lifeSpan = LIFE_SPAN,
                name = NAME.plus("1"),
                origin = null,
                referenceImageId = null,
                temperament = null,
                weight = aWeightDbModelWithNullValues(),
            ),
        )

    internal fun aDogBreedDbModel() =
        DogBreedDbModel(
            bredFor = BRED_FOR,
            breedGroup = BREED_GROUP,
            height = aHeightDbModel(),
            id = ID,
            image = anImageDbModel(),
            lifeSpan = LIFE_SPAN,
            name = NAME,
            origin = ORIGIN,
            referenceImageId = REFERENCE_IMAGE_ID,
            temperament = TEMPERAMENT,
            weight = aWeightDbModel(),
        )

    private fun aHeightDbModel() =
        HeightDbModel(
            metric = HEIGHT_METRIC,
            imperial = HEIGHT_IMPERIAL,
        )

    private fun aHeightDbModelWithNullValues() =
        HeightDbModel(
            metric = null,
            imperial = HEIGHT_IMPERIAL,
        )

    private fun aWeightDbModel() =
        WeightDbModel(
            metric = WEIGHT_METRIC,
            imperial = WEIGHT_IMPERIAL,
        )

    private fun aWeightDbModelWithNullValues() =
        WeightDbModel(
            metric = WEIGHT_METRIC,
            imperial = null,
        )

    private fun anImageDbModel() =
        ImageDbModel(
            height = IMAGE_HEIGHT,
            imageId = IMAGE_ID,
            url = IMAGE_URL,
            width = IMAGE_WIDTH,
        )

    private fun anImageDbModelWithNullValues() =
        ImageDbModel(
            height = IMAGE_HEIGHT,
            imageId = IMAGE_ID,
            url = null,
            width = IMAGE_WIDTH,
        )

    internal fun aDogBreedUiModelList() =
        listOf(
            aDogBreedUiModel(),
            aDogBreedUiModel().copy(name = NAME.plus(1), imageUrl = null)
        )

    internal fun aDogBreedUiModel() =
        DogBreedUiModel(
            id = ID,
            name = NAME,
            imageUrl = IMAGE_URL,
            sortOrder = SORT_ORDER,
        )

    internal fun aDogBreedSearchUiModelList() =
        listOf(
            DogBreedSearchUiModel(
                id = ID,
                breedName = NAME,
                breedGroup = BREED_GROUP,
                origin = ORIGIN,
            ),
            aDogBreedSearchUiModel(),
        )

    internal fun aDogBreedSearchUiModel() = DogBreedSearchUiModel(
        id = ID.plus(1),
        breedName = NAME.plus("1"),
        breedGroup = BREED_GROUP,
        origin = null,
    )

    internal fun aDogBreedDetailUiModel() =
        DogBreedDetailUiModel(
            breedName = NAME,
            breedGroup = BREED_GROUP,
            origin = null,
            temperament = TEMPERAMENT,
        )
}