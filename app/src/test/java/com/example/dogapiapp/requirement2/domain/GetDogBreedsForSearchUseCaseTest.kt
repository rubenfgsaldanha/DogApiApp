package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.data.repository.RepoResult
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetDogBreedsForSearchUseCaseTest {

    private val repository: DogBreedRepository = mock()
    private val getDogBreedsForSearchUseCase = GetDogBreedsForSearchUseCase(repository)

    @Test
    fun `should return Repo Success when repository returns Repo Success`() {
        val dogBreedDbModels = DogBreedsBuilder.aDogBreedDbModelList()
        val dogBreedSearchUiModel = DogBreedsBuilder.aDogBreedSearchUiModelList()
        val repoResult = RepoResult.Success(dogBreedDbModels)
        whenever(repository.getAllDogBreedsWithoutPagination()).thenReturn(Single.just(repoResult))

        val result = getDogBreedsForSearchUseCase().test()

        result.assertValue(RepoResult.Success(dogBreedSearchUiModel))
    }

    @Test
    fun `should return Repo Saved when repository returns Repo Saved`() {
        val dogBreedDbModels = DogBreedsBuilder.aDogBreedDbModelList()
        val dogBreedSearchUiModel = DogBreedsBuilder.aDogBreedSearchUiModelList()
        val repoResult = RepoResult.Saved(dogBreedDbModels)
        whenever(repository.getAllDogBreedsWithoutPagination()).thenReturn(Single.just(repoResult))

        val result = getDogBreedsForSearchUseCase().test()

        result.assertValue(RepoResult.Saved(dogBreedSearchUiModel))
    }

    @Test
    fun `should return Repo Error when repository returns Repo Error`() {
        val errorMsg = "Error"
        val repoResult = RepoResult.Error(errorMsg)
        whenever(repository.getAllDogBreedsWithoutPagination()).thenReturn(Single.just(repoResult))

        val result = getDogBreedsForSearchUseCase().test()

        result.assertValue(repoResult)
    }

    @Test
    fun `should return Repo Error when repository fails`() {
        val errorMsg = "Error"
        val repoResult = RepoResult.Error(errorMsg)
        whenever(repository.getAllDogBreedsWithoutPagination()).thenReturn(Single.error(Throwable(errorMsg)))

        val result = getDogBreedsForSearchUseCase().test()

        result.assertValue(repoResult)
    }
}