package com.example.dogapiapp.requirement1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.dogapiapp.requirement1.adapter.LINEAR_LAYOUT
import com.example.dogapiapp.requirement1.domain.GetDogBreedsWithPaginationUseCase
import com.example.dogapiapp.requirement1.domain.OrderDogBreedAlphabeticallyUseCase
import com.example.dogapiapp.requirement1.domain.OrderType
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesListViewModel @Inject constructor(
    private val getDogBreedsWithPaginationUseCase: GetDogBreedsWithPaginationUseCase,
    private val orderDogBreedAlphabeticallyUseCase: OrderDogBreedAlphabeticallyUseCase,
): ViewModel() {

    var currentLayout = LINEAR_LAYOUT

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getDogBreeds(): Flowable<PagingData<DogBreedUiModel>> {
        return getDogBreedsWithPaginationUseCase().cachedIn(viewModelScope)
    }

    fun orderAlphabetically(orderType: OrderType) {
        Completable.fromCallable {
            orderDogBreedAlphabeticallyUseCase(orderType)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
