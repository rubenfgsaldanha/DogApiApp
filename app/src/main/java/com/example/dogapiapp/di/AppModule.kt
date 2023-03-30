package com.example.dogapiapp.di

import com.example.dogapiapp.BuildConfig
import com.example.dogapiapp.requirement1.data.remote.api.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSportApi(): DogApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_DOG_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DogApi::class.java)
}
