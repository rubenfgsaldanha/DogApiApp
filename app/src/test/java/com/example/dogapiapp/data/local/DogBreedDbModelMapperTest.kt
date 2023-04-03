package com.example.dogapiapp.data.local

import com.example.dogapiapp.builder.DogBreedsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class DogBreedDbModelMapperTest {

    @Test
    fun `should map to Db model correctly`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedDtoResponse()
        val expected = DogBreedsBuilder.aDogBreedDbModelList()

        val result = dogBreeds.toDogBreedDbModelList()

        assertThat(result).isEqualTo(expected)
    }
}