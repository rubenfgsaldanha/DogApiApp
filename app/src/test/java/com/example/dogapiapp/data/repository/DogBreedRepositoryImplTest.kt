package com.example.dogapiapp.data.repository

import androidx.paging.PagingData
import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.local.dao.DogBreedDao
import com.example.dogapiapp.data.remote.ApiResult
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DogBreedRepositoryImplTest {

    private val dogBreedDao: DogBreedDao = mock()
    private val dataSource: DogBreedsRemoteDataSource = mock()
    private val repository = DogBreedRepositoryImpl(dogBreedDao, dataSource)

    @Test
    fun `getDogBreedsWithPagination returns a Flowable of PagingData with DogBreedDbModel items`() {
        val expected = PagingData.from(DogBreedsBuilder.aDogBreedDbModelList())
        whenever(dataSource.getDogBreedsWithPagination()).thenReturn(Flowable.just(expected))

        val result = repository.getDogBreedsWithPagination().test()

        result.assertValue(expected)
    }

    @Test
    fun `when insertAllDogBreeds should insert list of DogBreedDbModel into the database`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedDbModelList()
        repository.insertAllDogBreeds(dogBreeds)

        verify(dogBreedDao, only()).insertAll(dogBreeds)
    }

    @Test
    fun `when getDogBreedById should return RepoResult Saved if dog breed is in the database`() {
        val dogBreed = DogBreedsBuilder.aDogBreedDbModel()
        val expected = RepoResult.Saved(dogBreed)
        whenever(dogBreedDao.getDogBreedById(1)).thenReturn(Single.just(dogBreed))

        val result = repository.getDogBreedById(1).test()

        result.assertValue(expected)
    }

    @Test
    fun `when getDogBreedById should return RepoResult Error if error happens`() {
        val errorMsg = "Something went wrong"
        val expected = RepoResult.Error(errorMsg)
        whenever(dogBreedDao.getDogBreedById(1)).thenReturn(Single.error(Throwable(errorMsg)))

        val result = repository.getDogBreedById(1).test()

        result.assertValue(expected)
    }

    @Test
    fun `when getAllDogBreedsWithoutPaginationFromDb should return list from db`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedDbModelList()
        whenever(dogBreedDao.getDogBreedsFromDbWithoutPaging()).thenReturn(dogBreeds)

        val result = repository.getAllDogBreedsWithoutPaginationFromDb()

        assertThat(result).isEqualTo(dogBreeds)
    }

    @Test
    fun `when getAllDogBreedsWithoutPagination should save info to database and return RepoResult Success`() {
        val dogBreedsDto = DogBreedsBuilder.aDogBreedDtoResponse()
        val dogBreedsDbModel = DogBreedsBuilder.aDogBreedDbModelList()
        val expected = RepoResult.Success(dogBreedsDbModel)
        whenever(
            dataSource.getDogBreedsWithoutPagination()
        ).thenReturn(Single.just(ApiResult.Success(dogBreedsDto)))

        val result = repository.getAllDogBreedsWithoutPagination().test()

        result.assertValue(expected)
        verify(dogBreedDao).deleteAllDogBreeds()
        verify(dogBreedDao).insertAll(dogBreedsDbModel)
    }

    @Test
    fun `when getAllDogBreedsWithoutPagination fails should map Api error to RepoResult Error`() {
        val errorMsg = "Something went wrong"
        val expected = RepoResult.Error(errorMsg)
        whenever(
            dataSource.getDogBreedsWithoutPagination()
        ).thenReturn(Single.just(ApiResult.Error(errorMsg)))

        val result = repository.getAllDogBreedsWithoutPagination().test()

        result.assertValue(expected)
    }

    @Test
    fun `when getAllDogBreedsWithoutPagination should return RepoResult Error if error happens`() {
        val errorMsg = "Something went wrong"
        val expected = RepoResult.Error(errorMsg)
        whenever(
            dataSource.getDogBreedsWithoutPagination()
        ).thenReturn(Single.error(Throwable(errorMsg)))

        val result = repository.getAllDogBreedsWithoutPagination().test()

        result.assertValue(expected)
    }

    @Test
    fun `when deleteAllDogBreeds should delete all dog breeds from the database`() {
        repository.deleteAllDogBreeds()

        verify(dogBreedDao, only()).deleteAllDogBreeds()
    }
}