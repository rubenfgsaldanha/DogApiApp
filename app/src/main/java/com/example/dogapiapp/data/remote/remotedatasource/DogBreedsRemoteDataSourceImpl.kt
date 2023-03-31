package com.example.dogapiapp.data.remote.remotedatasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.remote.DogBreedRxRemoteMediator
import com.example.dogapiapp.data.remote.api.DogApi
import io.reactivex.Flowable
import javax.inject.Inject

class DogBreedsRemoteDataSourceImpl @Inject constructor(
    private val database: DogBreedDatabase,
    private val dogApi: DogApi,
): DogBreedsRemoteDataSource {

    companion object{
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 1
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PREFETCH_DISTANCE
            ),
            remoteMediator = DogBreedRxRemoteMediator(database, dogApi),
            pagingSourceFactory = { database.dogBreedDao().getDogBreedsFromDb() }
        ).flowable
    }
}