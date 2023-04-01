package com.example.dogapiapp.requirement1.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dogapiapp.requirement1.domain.GetDogBreedsWithPaginationUseCase
import com.example.dogapiapp.requirement1.domain.OrderDogBreedAlphabeticallyUseCase
import com.example.dogapiapp.requirement1.domain.OrderType
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesListViewModel @Inject constructor(
    private val getDogBreedsWithPaginationUseCase: GetDogBreedsWithPaginationUseCase,
    private val orderDogBreedAlphabeticallyUseCase: OrderDogBreedAlphabeticallyUseCase,
): ViewModel() {

    fun getDogBreeds(): Flowable<PagingData<DogBreedUiModel>> {
        return getDogBreedsWithPaginationUseCase()
    }

    fun orderAlphabetically(orderType: OrderType) {
        Completable.fromCallable {
            orderDogBreedAlphabeticallyUseCase(orderType)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
