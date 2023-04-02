package com.example.dogapiapp.data.remote.remotedatasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.dogapiapp.BuildConfig
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.toDogBreedDbModelList
import com.example.dogapiapp.data.remote.DogBreedRxRemoteMediator
import com.example.dogapiapp.data.remote.api.DogApi
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DogBreedsRemoteDataSourceImpl @Inject constructor(
    private val database: DogBreedDatabase,
    private val dogApi: DogApi,
): DogBreedsRemoteDataSource {

    companion object{
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 1
        const val BREEDS_ENDPOINT = "breeds"
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

    override fun getDogBreedsWithoutPagination(): Single<List<DogBreedDbModel>> {
        val url = BuildConfig.BASE_URL_DOG_API.plus(BREEDS_ENDPOINT)
        val headers = hashMapOf("x-api-key" to BuildConfig.API_KEY)

        return dogApi.getDogBreedsWithoutPagination(url, headers)
            .map {
                it.toDogBreedDbModelList()
            }
    }
}