package com.example.dogapiapp.requirement1.domain

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.DogBreedRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class OrderDogBreedAlphabeticallyUseCaseTest {

    private val repository: DogBreedRepository = mock()
    private val orderDogBreedAlphabeticallyUseCase = OrderDogBreedAlphabeticallyUseCase(repository)

    @Test
    fun `invoke with ASC order type should correctly order and update dog breed sort order`() {
        val defaultDbModel = DogBreedsBuilder.aDogBreedDbModel()
        val dogBreeds = listOf(
            defaultDbModel.copy(id = 1, name = "name3"),
            defaultDbModel.copy(id = 2, name = "name1"),
            defaultDbModel.copy(id = 3, name = "name2"),
        )
        val orderedItems = listOf(
            defaultDbModel.copy(id = 1, name = "name3", sortOrder = 3),
            defaultDbModel.copy(id = 3, name = "name2", sortOrder = 2),
            defaultDbModel.copy(id = 2, name = "name1", sortOrder = 1),
        )
        whenever(repository.getAllDogBreedsWithoutPaginationFromDb()).thenReturn(dogBreeds)

        orderDogBreedAlphabeticallyUseCase(OrderType.ASC)

        assertEquals(3, dogBreeds[0].sortOrder)
        assertEquals(1, dogBreeds[1].sortOrder)
        assertEquals(2, dogBreeds[2].sortOrder)
        verify(repository).deleteAllDogBreeds()
        verify(repository).insertAllDogBreeds(orderedItems)
    }

    @Test
    fun `invoke with DESC order type should correctly order and update dog breed sort order`() {
        val defaultDbModel = DogBreedsBuilder.aDogBreedDbModel()
        val dogBreeds = listOf(
            defaultDbModel.copy(id = 1, name = "name3"),
            defaultDbModel.copy(id = 2, name = "name1"),
            defaultDbModel.copy(id = 3, name = "name2"),
        )
        val orderedItems = listOf(
            defaultDbModel.copy(id = 2, name = "name1", sortOrder = 3),
            defaultDbModel.copy(id = 3, name = "name2", sortOrder = 2),
            defaultDbModel.copy(id = 1, name = "name3", sortOrder = 1),
        )
        whenever(repository.getAllDogBreedsWithoutPaginationFromDb()).thenReturn(dogBreeds)

        orderDogBreedAlphabeticallyUseCase(OrderType.DESC)

        assertEquals(1, dogBreeds[0].sortOrder)
        assertEquals(3, dogBreeds[1].sortOrder)
        assertEquals(2, dogBreeds[2].sortOrder)
        verify(repository).deleteAllDogBreeds()
        verify(repository).insertAllDogBreeds(orderedItems)
    }
}