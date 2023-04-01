package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDogBreedsForSearchUseCase @Inject constructor(
    //
) {

    operator fun invoke(): Single<List<DogBreedSearchUiModel>> {
        //
    }
}