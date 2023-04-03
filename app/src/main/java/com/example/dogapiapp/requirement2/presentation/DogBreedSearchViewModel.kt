package com.example.dogapiapp.requirement2.presentation

import androidx.lifecycle.ViewModel
import com.example.dogapiapp.data.repository.RepoResult
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

    var currentFilteredResults: List<DogBreedSearchUiModel> = emptyList()
    lateinit var allDogBreeds: Single<List<DogBreedSearchUiModel>>
    var shouldShowAllDogBreeds = true

    init {
        getDogBreeds()
    }

    private fun getDogBreeds() {
        allDogBreeds = getDogBreedsForSearchUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { repoResult ->
                val results = when (repoResult) {
                    is RepoResult.Success -> repoResult.data
                    is RepoResult.Saved -> repoResult.data
                    is RepoResult.Error -> throw Exception()
                }
                if(shouldShowAllDogBreeds) {
                    currentFilteredResults = results
                }
                results
            }
            .onErrorReturn {
                throw Exception()
            }
    }

    fun performSearch(text: CharSequence, list: List<DogBreedSearchUiModel>): List<DogBreedSearchUiModel> {
        shouldShowAllDogBreeds = false
        currentFilteredResults = performSearchUseCase(text.toString(), list)
        return currentFilteredResults
    }
}