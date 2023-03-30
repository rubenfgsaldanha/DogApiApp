package com.example.dogapiapp.data.remote.api

import com.example.dogapiapp.data.remote.dto.DogBreedDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface DogApi {

    @GET
    suspend fun getDogBreeds(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
    ): Single<List<DogBreedDto>>
}
