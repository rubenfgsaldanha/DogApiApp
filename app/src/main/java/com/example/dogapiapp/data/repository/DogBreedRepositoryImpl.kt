package com.example.dogapiapp.data.repository

import androidx.paging.PagingData
import com.example.dogapiapp.data.local.dao.DogBreedDao
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao,
    private val dataSource: DogBreedsRemoteDataSource,
): DogBreedRepository {

    override fun getDogBreedsWithPagination(): Flowable<PagingData<DogBreedDbModel>> {
        return dataSource.getDogBreedsWithPagination()
    }

    override fun insertAllDogBreeds(dogBreeds: List<DogBreedDbModel>) {
        dogBreedDao.insertAll(dogBreeds)
    }

    override fun getDogBreedById(id: Int): Single<DogBreedDbModel> {
        return dogBreedDao.getDogBreedById(id)
    }

    override fun getAllDogBreedsWithoutPagination(): List<DogBreedDbModel> {
        return dogBreedDao.getDogBreedsFromDbWithoutPaging()
    }

    override fun deleteAllDogBreeds() {
        dogBreedDao.deleteAllDogBreeds()
    }
}