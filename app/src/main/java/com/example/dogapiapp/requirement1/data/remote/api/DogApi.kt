package com.example.dogapiapp.requirement1.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogApi {

    @GET
    suspend fun getDogBreedImages(): Single<List<Void>>
}
