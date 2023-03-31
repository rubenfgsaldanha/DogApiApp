package com.example.dogapiapp.di

import android.content.Context
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.dao.DogBreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DogBreedDatabase = DogBreedDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideDogBreedDao(database: DogBreedDatabase): DogBreedDao = database.dogBreedDao()
}