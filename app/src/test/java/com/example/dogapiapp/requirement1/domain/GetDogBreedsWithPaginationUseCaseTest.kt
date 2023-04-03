package com.example.dogapiapp.requirement1.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import io.reactivex.rxjava3.core.Flowable
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetDogBreedsWithPaginationUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: DogBreedRepository = mock()
    private val getDogBreedsWithPaginationUseCase = GetDogBreedsWithPaginationUseCase(repository)

    @Test
    fun `should return Flowable of PagingData of DogBreedUiModel`() {
        val dogBreedsDbModels = PagingData.from(DogBreedsBuilder.aDogBreedDbModelList())
        whenever(repository.getDogBreedsWithPagination()).thenReturn(Flowable.just(dogBreedsDbModels))

        val result = getDogBreedsWithPaginationUseCase().blockingFirst()

        assert(result is PagingData<DogBreedUiModel>)
    }
}