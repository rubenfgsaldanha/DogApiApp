package com.example.dogapiapp.requirement3.presentation

import androidx.lifecycle.ViewModel
import com.example.dogapiapp.data.repository.RepoResult
import com.example.dogapiapp.requirement3.domain.GetDogBreedDetailUseCase
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailViewModel @Inject constructor(
    private val getDogBreedDetailUseCase: GetDogBreedDetailUseCase
): ViewModel() {

    var dogBreedId = 0

    fun getDogBreedById(): Single<List<DogBreedDetailUiModel>> {
        return getDogBreedDetailUseCase(dogBreedId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> repoResult.data
                    is RepoResult.Saved -> repoResult.data
                    is RepoResult.Error -> throw Exception()
                }
            }
            .onErrorReturn {
                throw Exception()
            }
    }
}