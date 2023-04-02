package com.example.dogapiapp.data.remote.api

import com.example.dogapiapp.data.remote.dto.DogBreedDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface DogApi {

    @GET
    fun getDogBreedsWithPagination(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @QueryMap queries: Map<String, Int>,
    ): Single<List<DogBreedDto>>

    @GET
    fun getDogBreedsWithoutPagination(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
    ): Single<List<DogBreedDto>>
}
