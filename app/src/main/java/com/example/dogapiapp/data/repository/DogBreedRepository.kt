package com.example.dogapiapp.data.repository

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import io.reactivex.Flowable

interface DogBreedRepository {

    fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>>
    fun insertAllDogBreeds(dogBreeds: List<DogBreedDbModel>)
    fun getAllDogBreedsWithoutPagination(): List<DogBreedDbModel>
    fun deleteAllDogBreeds()

}