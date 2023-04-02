package com.example.dogapiapp.requirement3.domain

import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.requirement3.mappers.toDogBreedDetailUiModel
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDogBreedDetailUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(id: Int): Single<List<DogBreedDetailUiModel>> {
        return repository.getDogBreedById(id).map {
            listOf(it.toDogBreedDetailUiModel())
        }
    }
}