package com.example.dogapiapp.requirement1.mappers

import com.example.dogapiapp.builder.DogBreedsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class DogBreedUiModelMapperTest {

    @Test
    fun `should map to ui model correctly`() {
        val dogBreed = DogBreedsBuilder.aDogBreedDbModel()
        val expected = DogBreedsBuilder.aDogBreedUiModel()

        val result = dogBreed.toDogBreedUiModel()

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `when it has null value should still map to ui model correctly`() {
        val dogBreed = DogBreedsBuilder.aDogBreedDbModel().copy(image = null)
        val expected = DogBreedsBuilder.aDogBreedUiModel().copy(imageUrl = null)

        val result = dogBreed.toDogBreedUiModel()

        assertThat(result).isEqualTo(expected)
    }
}