package com.example.dogapiapp.data.remote

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogapiapp.data.local.DogBreedDatabase
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.remote.api.MockDogApiForPagination
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
class DogBreedRxRemoteMediatorTest {

    private val mockApi = MockDogApiForPagination()
    private val mockDb = DogBreedDatabase.buildDatabase(
        ApplicationProvider.getApplicationContext(),
        useInMemory = true
    )

    @After
    fun tearDown(){
        mockDb.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() {
        mockApi.throwException = false
        mockApi.returnEmptyList = false
        val remoteMediator = DogBreedRxRemoteMediator(mockDb, mockApi)

        val pagingState = PagingState<Int, DogBreedDbModel>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(3),
            leadingPlaceholderCount = 3
        )

        remoteMediator.loadSingle(LoadType.REFRESH, pagingState)
            .test()
            .await()
            .assertValueCount(1)
            .assertValue { value ->
                value is RemoteMediator.MediatorResult.Success && !value.endOfPaginationReached
            }
    }
}