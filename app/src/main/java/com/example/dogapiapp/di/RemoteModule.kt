package com.example.dogapiapp.di

import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideDogBreedsRemoteDataSource(dataSourceImpl: DogBreedsRemoteDataSourceImpl): DogBreedsRemoteDataSource = dataSourceImpl
}
