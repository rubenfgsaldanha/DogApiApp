package com.example.dogapiapp.requirement2.mappers

import com.example.dogapiapp.builder.DogBreedsBuilder
import org.assertj.core.api.Assertions
import org.junit.Test

internal class DogBreedSearchUiModelMapperTest {

    @Test
    fun `should map to search ui model correctly`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedDbModelList()
        val expected = DogBreedsBuilder.aDogBreedSearchUiModelList()

        val result = dogBreeds.toDogBreedSearchUiModelList()

        Assertions.assertThat(result).isEqualTo(expected)
    }
}