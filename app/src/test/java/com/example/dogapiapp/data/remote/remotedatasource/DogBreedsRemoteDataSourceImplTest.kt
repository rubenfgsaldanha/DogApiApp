package com.example.dogapiapp.data.remote.remotedatasource

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.remote.ApiResult
import com.example.dogapiapp.data.remote.api.DogApi
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DogBreedsRemoteDataSourceImplTest {

    private val database: DogBreedDatabase = mock()
    private val dogApi: DogApi = mock()
    private val dogBreedsRemoteDataSourceImpl = DogBreedsRemoteDataSourceImpl(database, dogApi)

    @Test
    fun `when getDogBreedsWithoutPagination should return ApiResult of List of DogBreedDto`() {
        val dogBreedDtoList = DogBreedsBuilder.aDogBreedDtoResponse()
        val expected = ApiResult.Success(dogBreedDtoList)
        whenever(
            dogApi.getDogBreedsWithoutPagination(anyString(), anyMap())
        ).thenReturn(Single.just(dogBreedDtoList))

        val result = dogBreedsRemoteDataSourceImpl.getDogBreedsWithoutPagination()

        assertThat(result.blockingGet()).isEqualTo(expected)
    }
}