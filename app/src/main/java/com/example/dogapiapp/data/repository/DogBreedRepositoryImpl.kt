package com.example.dogapiapp.data.repository

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val dataSource: DogBreedsRemoteDataSource,
): DogBreedRepository {

    override fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>> {
        return dataSource.getDogBreedsWithPagination()
    }
}