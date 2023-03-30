package com.example.dogapiapp.di

import android.content.Context
import com.example.dogapiapp.BuildConfig
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.dao.DogBreedDao
import com.example.dogapiapp.data.remote.api.DogApi
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSource
import com.example.dogapiapp.data.remote.remotedatasource.DogBreedsRemoteDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DogBreedDatabase = DogBreedDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideDogBreedDao(database: DogBreedDatabase): DogBreedDao = database.dogBreedDao()

    @Provides
    @Singleton
    fun provideDogBreedsRemoteDataSource(dataSourceImpl: DogBreedsRemoteDataSourceImpl): DogBreedsRemoteDataSource = dataSourceImpl

    @Provides
    @Singleton
    fun provideDogBreedApi(): DogApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_DOG_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DogApi::class.java)
}
