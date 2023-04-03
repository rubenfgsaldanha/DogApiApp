package com.example.dogapiapp.requirement2.presentation

import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.helper.RxImmediateSchedulerRule
import com.example.dogapiapp.requirement2.domain.GetDogBreedsForSearchUseCase
import com.example.dogapiapp.requirement2.domain.PerformSearchUseCase
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DogBreedSearchViewModelTest {

    private val getDogBreedsForSearchUseCase: GetDogBreedsForSearchUseCase = mock()
    private val performSearchUseCase: PerformSearchUseCase = mock()
    private val viewModel = DogBreedSearchViewModel(
        getDogBreedsForSearchUseCase,
        performSearchUseCase
    )

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `when getDogBreeds repo success should return data`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedSearchUiModelList()
        val repoResult = RepoResult.Success(dogBreeds)
        whenever(getDogBreedsForSearchUseCase()).thenReturn(Single.just(repoResult))

        val result = viewModel.allDogBreeds.test()

        result.assertValue(dogBreeds)
        assertThat(viewModel.currentFilteredResults).isEqualTo(dogBreeds)
    }

    @Test
    fun `when getDogBreeds repo saved should return data`() {
        val dogBreeds = DogBreedsBuilder.aDogBreedSearchUiModelList()
        val repoResult = RepoResult.Saved(dogBreeds)
        viewModel.shouldShowAllDogBreeds = false
        whenever(getDogBreedsForSearchUseCase()).thenReturn(Single.just(repoResult))

        val result = viewModel.allDogBreeds.test()

        result.assertValue(dogBreeds)
        assertThat(viewModel.currentFilteredResults).isNotEqualTo(dogBreeds)
    }

    @Test
    fun `when getDogBreeds repo error should not return data`() {
        val errorMsg = "Error"
        val repoResult = RepoResult.Error(errorMsg)
        whenever(getDogBreedsForSearchUseCase()).thenReturn(Single.just(repoResult))

        val result = viewModel.allDogBreeds.test()

        result.assertNoValues()
        assertThat(viewModel.currentFilteredResults).isEmpty()
    }

    @Test
    fun `when getDogBreeds error should throw exception`() {
        val errorMsg = "Error"
        whenever(getDogBreedsForSearchUseCase()).thenReturn(Single.error(Throwable(errorMsg)))

        val result = viewModel.allDogBreeds.test()

        result.assertNoValues()
        assertThat(viewModel.currentFilteredResults).isEmpty()
    }

    @Test
    fun `should filter dog breeds based on search text`() {
        val searchText = "a"
        val dogBreeds = DogBreedsBuilder.aDogBreedSearchUiModelList().plus(
            DogBreedSearchUiModel(id = 5, breedName = "corgi", breedGroup = "dfrg", origin = "wgfevtf")
        )
        val filteredDogBreeds = DogBreedsBuilder.aDogBreedSearchUiModelList()
        whenever(performSearchUseCase(searchText, dogBreeds)).thenReturn(filteredDogBreeds)

        viewModel.performSearch(searchText, dogBreeds)


        assertThat(viewModel.currentFilteredResults).isEqualTo(filteredDogBreeds)
    }
}