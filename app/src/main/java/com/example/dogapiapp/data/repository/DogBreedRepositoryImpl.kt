package com.example.dogapiapp.data.repository

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.dao.DogBreedDao
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.toDogBreedDbModelList
import com.example.dogapiapp.data.remote.ApiResult
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao,
    private val dataSource: DogBreedsRemoteDataSource,
) : DogBreedRepository {

    override fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>> {
        return dataSource.getDogBreedsWithPagination()
    }

    override fun insertAllDogBreeds(dogBreeds: List<DogBreedDbModel>) {
        dogBreedDao.insertAll(dogBreeds)
    }

    override fun getDogBreedById(id: Int): Single<RepoResult<DogBreedDbModel>> {
        return dogBreedDao.getDogBreedById(id)
            .map<RepoResult<DogBreedDbModel>> {
                RepoResult.Saved(it)
            }
            .onErrorReturn {
                RepoResult.Error(it.message.toString())
            }
    }

    override fun getAllDogBreedsWithoutPaginationFromDb(): List<DogBreedDbModel> {
        return dogBreedDao.getDogBreedsFromDbWithoutPaging()
    }

    override fun getAllDogBreedsWithoutPagination(): Single<RepoResult<List<DogBreedDbModel>>> {
        return dataSource.getDogBreedsWithoutPagination()
            .map { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val dogBreeds = apiResult.data.toDogBreedDbModelList()
                        dogBreedDao.deleteAllDogBreeds()
                        dogBreedDao.insertAll(dogBreeds)
                        RepoResult.Success(dogBreeds)
                    }
                    is ApiResult.Error -> {
                        RepoResult.Error(apiResult.errorMessage)
                    }
                }
            }
            .onErrorReturn {
                RepoResult.Error(it.message.toString())
            }
    }

    override fun deleteAllDogBreeds() {
        dogBreedDao.deleteAllDogBreeds()
    }
}