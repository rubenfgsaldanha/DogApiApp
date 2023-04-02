package com.example.dogapiapp.data.remote.remotedatasource

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DogBreedsRemoteDataSource {

    fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>>
    fun getDogBreedsWithoutPagination(): Single<List<DogBreedDbModel>>
}