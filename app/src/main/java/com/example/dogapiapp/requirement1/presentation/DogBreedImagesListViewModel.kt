package com.example.dogapiapp.requirement1.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dogapiapp.requirement1.domain.GetDogBreedsWithPaginationUseCase
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesListViewModel @Inject constructor(
    private val getDogBreedsWithPaginationUseCase: GetDogBreedsWithPaginationUseCase,
): ViewModel() {

    fun getDogBreeds(): Flowable<PagingData<DogBreedUiModel>> {
        return getDogBreedsWithPaginationUseCase()
    }
}
