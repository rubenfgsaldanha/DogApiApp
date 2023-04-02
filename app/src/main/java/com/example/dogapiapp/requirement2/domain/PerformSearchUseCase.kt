package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import javax.inject.Inject

class PerformSearchUseCase @Inject constructor() {

    operator fun invoke(text: String, list: List<DogBreedSearchUiModel>): List<DogBreedSearchUiModel> {
        return list.filter { it.breedName?.lowercase()?.contains(text) == true }
    }
}