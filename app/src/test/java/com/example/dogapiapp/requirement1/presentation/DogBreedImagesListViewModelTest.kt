package com.example.dogapiapp.requirement1.presentation

import androidx.paging.PagingData
import com.example.dogapiapp.builder.DogBreedsBuilder
import com.example.dogapiapp.requirement1.domain.GetDogBreedsWithPaginationUseCase
import com.example.dogapiapp.requirement1.domain.OrderDogBreedAlphabeticallyUseCase
import com.example.dogapiapp.requirement1.domain.OrderType
import com.example.dogapiapp.requirement1.model.DogBreedUiModel
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DogBreedImagesListViewModelTest {

    private val getDogBreedsWithPaginationUseCase: GetDogBreedsWithPaginationUseCase = mock()
    private val orderDogBreedAlphabeticallyUseCase: OrderDogBreedAlphabeticallyUseCase = mock()
    private val viewModel = DogBreedImagesListViewModel(
        getDogBreedsWithPaginationUseCase,
        orderDogBreedAlphabeticallyUseCase
    )

    @Test
    fun `when get dog breeds should return Flowable of PagingData of DogBreedUiModel`() {
        val dogBreeds = PagingData.from(DogBreedsBuilder.aDogBreedUiModelList())
        whenever(getDogBreedsWithPaginationUseCase()).thenReturn(Flowable.just(dogBreeds))

        val result = viewModel.getDogBreeds().blockingFirst()

        assert(result is PagingData<DogBreedUiModel>)
    }

    @Test
    fun `when orderAlphabetically should call the use case`() {
        val orderType = OrderType.ASC

        viewModel.orderAlphabetically(orderType)

        verify(orderDogBreedAlphabeticallyUseCase)(orderType)
    }
}