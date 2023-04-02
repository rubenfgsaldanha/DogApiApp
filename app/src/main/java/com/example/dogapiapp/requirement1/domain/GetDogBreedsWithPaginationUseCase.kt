package com.example.dogapiapp.requirement1.domain

import androidx.paging.PagingData
import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.requirement1.mappers.toPagingDataUiModel
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetDogBreedsWithPaginationUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(): Flowable<PagingData<DogBreedUiModel>> {
        return repository.getDogBreedsWithPagination()
            .map {
                it.toPagingDataUiModel()
            }
    }
}