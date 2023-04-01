package com.example.dogapiapp.requirement2.presentation

import androidx.lifecycle.ViewModel
import com.example.dogapiapp.requirement2.domain.GetDogBreedsForSearchUseCase
import com.example.dogapiapp.requirement2.domain.PerformSearchUseCase
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class DogBreedSearchViewModel @Inject constructor(
    private val getDogBreedsForSearchUseCase: GetDogBreedsForSearchUseCase,
    private val performSearchUseCase: PerformSearchUseCase,
): ViewModel() {

    val someList = listOf(
        DogBreedSearchUiModel(
            id = 1,
            breedName = "mimo",
            breedGroup = "dog",
            origin = "espanha"
        ),
        DogBreedSearchUiModel(
            id = 2,
            breedName = "storm",
            breedGroup = "cat",
            origin = "portugal"
        ),
        DogBreedSearchUiModel(
            id = 3,
            breedName = "cloud",
            breedGroup = "cat",
            origin = "portugal"
        ),
        DogBreedSearchUiModel(
            id = 4,
            breedName = "ozzy",
            breedGroup = "dog",
            origin = "portugal"
        )
    )

    fun getDogBreeds(): Single<List<DogBreedSearchUiModel>> {
        return getDogBreedsForSearchUseCase()
    }

    fun performSearch(text: CharSequence, list: List<DogBreedSearchUiModel>): List<DogBreedSearchUiModel> {
        return performSearchUseCase(text.toString(), list)
    }
}