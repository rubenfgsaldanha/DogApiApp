package com.example.dogapiapp.requirement2.domain

import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.requirement2.mappers.toDogBreedSearchUiModelList
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDogBreedsForSearchUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(): Single<RepoResult<List<DogBreedSearchUiModel>>> {
        return repository.getAllDogBreedsWithoutPagination()
            .map { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> RepoResult.Success(repoResult.data.toDogBreedSearchUiModelList())
                    is RepoResult.Saved -> RepoResult.Saved(repoResult.data.toDogBreedSearchUiModelList())
                    is RepoResult.Error -> repoResult
                }
            }
            .onErrorReturn {
                RepoResult.Error(it.message.toString())
            }
    }
}