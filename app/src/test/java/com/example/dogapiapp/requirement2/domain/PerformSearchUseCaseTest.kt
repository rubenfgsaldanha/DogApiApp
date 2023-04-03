package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PerformSearchUseCaseTest {

    private val performSearchUseCase = PerformSearchUseCase()

    @Test
    fun `should return a list of matching breed names`() {
        val text = "1"
        val initialList = DogBreedsBuilder.aDogBreedSearchUiModelList().plus(
            DogBreedSearchUiModel(
                id = 3,
                breedName = null,
                breedGroup = "some breed group",
                origin = "origin"
            )
        )
        val expected = listOf(DogBreedsBuilder.aDogBreedSearchUiModel())

        val result = performSearchUseCase(text, initialList)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return empty list when given empty list`() {
        val text = "poodle"
        val list = emptyList<DogBreedSearchUiModel>()

        val result = performSearchUseCase(text, list)

        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list when text doesn't match any items`() {
        val text = "retvgrydjh"
        val initialList = DogBreedsBuilder.aDogBreedSearchUiModelList()

        val result = performSearchUseCase(text, initialList)

        assertThat(result).isEmpty()
    }

    @Test
    fun `should return all items when text is empty`() {
        val text = ""
        val initialList = DogBreedsBuilder.aDogBreedSearchUiModelList()

        val result = performSearchUseCase(text, initialList)

        assertThat(result).isEqualTo(initialList)
    }
}