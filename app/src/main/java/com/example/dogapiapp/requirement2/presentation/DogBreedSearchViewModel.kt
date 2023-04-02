package com.example.dogapiapp.requirement2.presentation

import androidx.lifecycle.ViewModel
import com.example.dogapiapp.requirement2.domain.GetDogBreedsForSearchUseCase
import com.example.dogapiapp.requirement2.domain.PerformSearchUseCase
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DogBreedSearchViewModel @Inject constructor(
    private val getDogBreedsForSearchUseCase: GetDogBreedsForSearchUseCase,
    private val performSearchUseCase: PerformSearchUseCase,
): ViewModel() {

    fun getDogBreeds(): Single<List<DogBreedSearchUiModel>> {
        return getDogBreedsForSearchUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun performSearch(text: CharSequence, list: List<DogBreedSearchUiModel>): List<DogBreedSearchUiModel> {
        return performSearchUseCase(text.toString(), list)
    }
}