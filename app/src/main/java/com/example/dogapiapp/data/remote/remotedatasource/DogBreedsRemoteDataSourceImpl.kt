package com.example.dogapiapp.data.remote.remotedatasource

import com.example.dogapiapp.BuildConfig
import com.example.dogapiapp.data.remote.api.DogApi
import com.example.dogapiapp.data.remote.dto.DogBreedDto
import com.example.dogapiapp.model.DogBreedModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.HeaderMap
import javax.inject.Inject

class DogBreedsRemoteDataSourceImpl @Inject constructor(
    private val dogApi: DogApi,
): DogBreedsRemoteDataSource {

    override suspend fun getDogBreeds(): Single<List<DogBreedModel>> {
        val headers = hashMapOf("x-api-key" to BuildConfig.API_KEY)

        return dogApi.getDogBreeds(
            url = BuildConfig.BASE_URL_DOG_API,
            headers = headers,
        )
            .onErrorResumeNext {
                Single.error(it)
            }
            .flatMap {
                Single.just(it.toDogBreedModel())
            }
    }
}