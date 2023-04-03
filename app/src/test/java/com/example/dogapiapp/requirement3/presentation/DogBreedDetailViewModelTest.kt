package com.example.dogapiapp.requirement3.presentation

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.requirement3.domain.GetDogBreedDetailUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class DogBreedDetailViewModelTest {

    private val getDogBreedDetailUseCase: GetDogBreedDetailUseCase = mock()
    private val viewModel = DogBreedDetailViewModel(getDogBreedDetailUseCase)

    @Test
    fun `when getDogBreedById success should return data`() {
        val dogBreedId = 1
        val dogBreedDetails = listOf(DogBreedsBuilder.aDogBreedDetailUiModel())
        val repoResult = RepoResult.Success(dogBreedDetails)
        whenever(getDogBreedDetailUseCase(dogBreedId)).thenReturn(Single.just(repoResult))

        viewModel.dogBreedId = dogBreedId
        val result = viewModel.getDogBreedById().test()

        result.assertValue(dogBreedDetails)
    }

    fun `when getDogBreedById saved should return data`() {}

    fun `when getDogBreedById error should throw exception`() {}
}