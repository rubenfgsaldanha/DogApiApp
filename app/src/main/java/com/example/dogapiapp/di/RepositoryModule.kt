package com.example.dogapiapp.di

import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.data.repository.DogBreedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDogBreedRepository(repositoryImpl: DogBreedRepositoryImpl): DogBreedRepository = repositoryImpl
}