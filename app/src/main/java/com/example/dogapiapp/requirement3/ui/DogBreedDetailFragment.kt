package com.example.dogapiapp.requirement3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dogapiapp.databinding.FragmentDogBreedDetailBinding
import com.example.dogapiapp.requirement3.adapter.DogBreedDetailRenderer
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel
import com.example.dogapiapp.requirement3.presentation.DogBreedDetailViewModel
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class DogBreedDetailFragment: Fragment() {

    private lateinit var binding: FragmentDogBreedDetailBinding
    private val viewModel: DogBreedDetailViewModel by viewModels()

    private val mDisposable = CompositeDisposable()
    private val adapter by lazy(::createAdapter)

    private fun createAdapter() =
        RendererBuilder<Any>()
            .bind(DogBreedDetailUiModel::class.java, DogBreedDetailRenderer())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {  bundle ->
            DogBreedDetailFragmentArgs.fromBundle(bundle).also {
                viewModel.dogBreedId = it.dogBreedId
            }
        }

        mDisposable.add(
            viewModel.getDogBreedById().subscribe(
                { dogBreedDetails ->
                    binding.dobBreedDetailList.adapter = RVRendererAdapter(adapter, dogBreedDetails)
                },
                { error ->
                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }
}