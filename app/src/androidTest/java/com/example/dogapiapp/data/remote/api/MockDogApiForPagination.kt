package com.example.dogapiapp.data.remote.api

import com.example.dogapiapp.data.remote.dto.DogBreedDto
import io.reactivex.rxjava3.core.Single

class MockDogApiForPagination : DogApi {

    var throwException = false
    var returnEmptyList = false

    override fun getDogBreedsWithPagination(
        url: String,
        headers: Map<String, String>,
        queries: Map<String, Int>
    ): Single<List<DogBreedDto>> {
        return when {
            throwException -> throw Exception()
            returnEmptyList -> Single.just(emptyList())
            else -> Single.just(createMockList())
        }
    }

    override fun getDogBreedsWithoutPagination(
        url: String,
        headers: Map<String, String>
    ): Single<List<DogBreedDto>> {
        return Single.just(emptyList())
    }

    private fun createMockList() =
        listOf(
            DogBreedDto(
                bredFor = "bred_for",
                breedGroup = "breed_group",
                height = null,
                id = 1,
                image = null,
                lifeSpan = "life_span",
                name = "name",
                origin = "origin",
                referenceImageId = "reference_image_id",
                temperament = "temperament",
                weight = null,
            ),
            DogBreedDto(
                bredFor = "bred_for2",
                breedGroup = "breed_group2",
                height = null,
                id = 2,
                image = null,
                lifeSpan = "life_span2",
                name = "name2",
                origin = "origin2",
                referenceImageId = "reference_image_id2",
                temperament = "temperament2",
                weight = null,
            ),
            DogBreedDto(
                bredFor = "bred_for3",
                breedGroup = "breed_group3",
                height = null,
                id = 3,
                image = null,
                lifeSpan = "life_span3",
                name = "name3",
                origin = "origin3",
                referenceImageId = "reference_image_id3",
                temperament = "temperament3",
                weight = null,
            ),
            DogBreedDto(
                bredFor = "bred_for4",
                breedGroup = "breed_group4",
                height = null,
                id = 4,
                image = null,
                lifeSpan = "life_span4",
                name = "name4",
                origin = "origin4",
                referenceImageId = "reference_image_id4",
                temperament = "temperament4",
                weight = null,
            ),
            DogBreedDto(
                bredFor = "bred_for5",
                breedGroup = "breed_group5",
                height = null,
                id = 5,
                image = null,
                lifeSpan = "life_span5",
                name = "name5",
                origin = "origin5",
                referenceImageId = "reference_image_id5",
                temperament = "temperament5",
                weight = null,
            ),
        )
}