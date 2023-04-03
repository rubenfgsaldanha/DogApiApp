package com.example.dogapiapp.data.remote.remotedatasource

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.remote.ApiResult
import com.example.dogapiapp.data.remote.dto.DogBreedDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DogBreedsRemoteDataSource {

    fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>>
    fun getDogBreedsWithoutPagination(): Single<ApiResult<List<DogBreedDto>>>
}