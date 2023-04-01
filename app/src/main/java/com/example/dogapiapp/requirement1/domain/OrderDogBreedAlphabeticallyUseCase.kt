package com.example.dogapiapp.requirement1.domain

import com.example.dogapiapp.data.repository.DogBreedRepository
import javax.inject.Inject

class OrderDogBreedAlphabeticallyUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(orderType: OrderType) {
        val items = repository.getAllDogBreedsWithoutPagination()

        val reversedItems = if (orderType == OrderType.DESC) {
            items.sortedBy { it.name }
        } else {
            items.sortedBy { it.name }.reversed()
        }

        val totalRows = reversedItems.size
        reversedItems.forEachIndexed { index, dogBreedDbModel ->
            dogBreedDbModel.sortOrder = totalRows - index
        }

        repository.deleteAllDogBreeds()
        repository.insertAllDogBreeds(reversedItems)
    }
}