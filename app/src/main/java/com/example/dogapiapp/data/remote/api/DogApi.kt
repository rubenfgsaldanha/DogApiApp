package com.example.dogapiapp.data.remote.api

import com.example.dogapiapp.data.remote.dto.DogBreedDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface DogApi {

    @GET
    fun getDogBreeds(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @QueryMap queries: Map<String, Int>,
    ): Single<List<DogBreedDto>>
}
