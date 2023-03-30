package com.example.dogapiapp.data.remote.remotedatasource

import com.example.dogapiapp.model.DogBreedModel
import io.reactivex.rxjava3.core.Single

interface DogBreedsRemoteDataSource {

    suspend fun getDogBreeds(): Single<List<DogBreedModel>>
}