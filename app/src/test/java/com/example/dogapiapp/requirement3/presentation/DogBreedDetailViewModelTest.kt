package com.example.dogapiapp.requirement3.presentation

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.helper.RxImmediateSchedulerRule
import com.example.dogapiapp.requirement3.domain.GetDogBreedDetailUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DogBreedDetailViewModelTest {

    private val getDogBreedDetailUseCase: GetDogBreedDetailUseCase = mock()
    private val viewModel = DogBreedDetailViewModel(getDogBreedDetailUseCase)

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `when getDogBreedById repo success should return data`() {
        val dogBreedId = 1
        val dogBreedDetails = listOf(DogBreedsBuilder.aDogBreedDetailUiModel())
        val repoResult = RepoResult.Success(dogBreedDetails)
        viewModel.dogBreedId = dogBreedId
        whenever(getDogBreedDetailUseCase(dogBreedId)).thenReturn(Single.just(repoResult))

        val result = viewModel.getDogBreedById().test()

        result.assertValue(dogBreedDetails)
    }

    @Test
    fun `when getDogBreedById repo saved should return data`() {
        val dogBreedId = 1
        val dogBreedDetails = listOf(DogBreedsBuilder.aDogBreedDetailUiModel())
        val repoResult = RepoResult.Saved(dogBreedDetails)
        viewModel.dogBreedId = dogBreedId
        whenever(getDogBreedDetailUseCase(dogBreedId)).thenReturn(Single.just(repoResult))

        val result = viewModel.getDogBreedById().test()

        result.assertValue(dogBreedDetails)
    }

    @Test
    fun `when getDogBreedById repo error should not return data`() {
        val dogBreedId = 1
        val errorMsg = "error"
        val repoResult = RepoResult.Error(errorMsg)
        viewModel.dogBreedId = dogBreedId
        whenever(getDogBreedDetailUseCase(dogBreedId)).thenReturn(Single.just(repoResult))

        val result = viewModel.getDogBreedById().test()

        result.assertNoValues()
    }

    @Test
    fun `when getDogBreedById error should throw exception`() {
        val dogBreedId = 1
        val errorMsg = "error"
        viewModel.dogBreedId = dogBreedId
        whenever(getDogBreedDetailUseCase(dogBreedId)).thenReturn(Single.error(Throwable(errorMsg)))

        val result = viewModel.getDogBreedById().test()

        result.assertNoValues()
    }
}