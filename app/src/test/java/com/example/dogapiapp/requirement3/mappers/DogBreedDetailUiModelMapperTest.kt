package com.example.dogapiapp.requirement3.mappers

import com.example.dogapiapp.builder.DogBreedsBuilder
import org.assertj.core.api.Assertions
import org.junit.Test

internal class DogBreedDetailUiModelMapperTest {

    @Test
    fun `should map to detail ui model correctly`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedDbModel().copy(origin = null)
        val expected = DogBreedsBuilder.aDogBreedDetailUiModel()

        val result = dogBreeds.toDogBreedDetailUiModel()

        Assertions.assertThat(result).isEqualTo(expected)
    }
}