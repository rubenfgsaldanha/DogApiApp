package com.example.dogapiapp.requirement3.domain

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.requirement3.mappers.toDogBreedDetailUiModel
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetDogBreedDetailUseCaseTest {

    private val repository: DogBreedRepository = mock()
    private val getDogBreedDetailUseCase = GetDogBreedDetailUseCase(repository)

    @Test
    fun `should return Repo Success when repository returns Repo Success`() {
        val id = 1
        val dogBreedDbModel = DogBreedsBuilder.aDogBreedDbModel().copy(origin = null)
        val dogBreedDetailUiModel = DogBreedsBuilder.aDogBreedDetailUiModel()
        val repoResult = RepoResult.Success(dogBreedDbModel)
        whenever(repository.getDogBreedById(id)).thenReturn(Single.just(repoResult))

        val result = getDogBreedDetailUseCase(id).test()

        result.assertValue(RepoResult.Success(listOf(dogBreedDetailUiModel)))
    }

    @Test
    fun `should return Repo Saved when repository returns Repo Saved`() {
        val id = 1
        val dogBreedDbModel = DogBreedsBuilder.aDogBreedDbModel().copy(origin = null)
        val dogBreedDetailUiModel = DogBreedsBuilder.aDogBreedDetailUiModel()
        val repoResult = RepoResult.Saved(dogBreedDbModel)
        whenever(repository.getDogBreedById(id)).thenReturn(Single.just(repoResult))

        val result = getDogBreedDetailUseCase(id).test()

        result.assertValue(RepoResult.Saved(listOf(dogBreedDetailUiModel)))
    }

    @Test
    fun `should return Repo Error when repository returns Repo Error`() {
        val id = 1
        val errorMsg = "Error"
        val repoResult = RepoResult.Error(errorMsg)
        whenever(repository.getDogBreedById(id)).thenReturn(Single.just(repoResult))

        val result = getDogBreedDetailUseCase(id).test()

        result.assertValue(repoResult)
    }
}
