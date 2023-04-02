package com.example.dogapiapp.data.remote

import android.annotation.SuppressLint
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.example.dogapiapp.BuildConfig
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.model.DogBreedRemoteKeys
import com.example.dogapiapp.data.local.toDogBreedDbModel
import com.example.dogapiapp.data.local.toDogBreedDbModelList
import com.example.dogapiapp.data.remote.api.DogApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class DogBreedRxRemoteMediator constructor(
    private val database: DogBreedDatabase,
    private val dogApi: DogApi,
): RxRemoteMediator<Int, DogBreedDbModel>() {

    companion object {
        const val STARTING_PAGE_INDEX = 0
        const val INVALID_PAGE = -1
        const val BREEDS_ENDPOINT = "breeds"
    }

    private var lastItemOfCurrentPage: DogBreedDbModel? = null

    @SuppressLint("CheckResult")
    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, DogBreedDbModel>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)

                        remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem()
                            ?: throw InvalidObjectException("Error getting last remote key")

                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                    LoadType.PREPEND -> INVALID_PAGE
                }
            }
            .flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    val url = BuildConfig.BASE_URL_DOG_API.plus(BREEDS_ENDPOINT)
                    val queryParams = hashMapOf(
                        "page" to page,
                        "limit" to state.config.pageSize,
                    )
                    val headers = hashMapOf("x-api-key" to BuildConfig.API_KEY)

                    dogApi.getDogBreedsWithPagination(url, headers, queryParams)
                        .map<MediatorResult> { response ->
                            val endOfPaginationReached = response.isEmpty()
                            lastItemOfCurrentPage = response.last().toDogBreedDbModel()

                            val dbModel = response.toDogBreedDbModelList()
                            insertToDb(page, loadType, dbModel, endOfPaginationReached)

                            MediatorResult.Success(endOfPaginationReached)
                        }
                        .onErrorReturn { MediatorResult.Error(it) }
                }
            }
            .onErrorReturn { MediatorResult.Error(it) }
    }

    private fun insertToDb(
        page: Int,
        loadType: LoadType,
        data: List<DogBreedDbModel>,
        endOfPagination: Boolean
    ): List<DogBreedDbModel> {
        database.runInTransaction {
            if (loadType == LoadType.REFRESH) {
                database.dogBreedDao().deleteAllRemoteKeys()
                database.dogBreedDao().deleteAllDogBreeds()
            }

            val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
            val nextKey = if (endOfPagination) null else page + 1
            val keys = data.map {
                DogBreedRemoteKeys(id = it.id, previousKey = prevKey, nextKey = nextKey)
            }

            database.dogBreedDao().insertAllRemoteKeys(keys)
            database.dogBreedDao().insertAll(data)
        }

        return data
    }

    private fun getRemoteKeyForLastItem(): DogBreedRemoteKeys? {
        return lastItemOfCurrentPage?.id?.let {
            database.dogBreedDao().getDogBreedRemoteKeyById(it)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, DogBreedDbModel>): DogBreedRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.dogBreedDao().getDogBreedRemoteKeyById(id)
            }
        }
    }
}