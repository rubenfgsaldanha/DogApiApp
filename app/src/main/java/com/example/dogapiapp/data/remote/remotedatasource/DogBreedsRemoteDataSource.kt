package com.example.dogapiapp.data.remote.remotedatasource

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import io.reactivex.Flowable

interface DogBreedsRemoteDataSource {

    fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>>
}