package com.example.dogapiapp.requirement3.domain

import com.example.dogapiapp.data.repository.DogBreedRepository
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.requirement3.mappers.toDogBreedDetailUiModel
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDogBreedDetailUseCase @Inject constructor(
    private val repository: DogBreedRepository,
) {

    operator fun invoke(id: Int): Single<RepoResult<List<DogBreedDetailUiModel>>> {
        return repository.getDogBreedById(id)
            .map { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> RepoResult.Success(listOf(repoResult.data.toDogBreedDetailUiModel()))
                    is RepoResult.Saved -> RepoResult.Saved(listOf(repoResult.data.toDogBreedDetailUiModel()))
                    is RepoResult.Error -> repoResult
                }
            }
    }
}