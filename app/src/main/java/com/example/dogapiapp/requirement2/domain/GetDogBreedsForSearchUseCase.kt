package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.requirement2.mappers.toDogBreedSearchUiModelList
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDogBreedsForSearchUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(): Single<List<DogBreedSearchUiModel>> {
        return repository.getAllDogBreedsWithoutPagination().map {
            it.toDogBreedSearchUiModelList()
        }
    }
}