package com.example.dogapiapp.requirement1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapiapp.databinding.FragmentDogBreedImagesListBinding
import com.example.dogapiapp.requirement1.adapter.DogBreedImagesAdapter
import com.example.dogapiapp.requirement1.presentation.DogBreedImagesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class DogBreedImagesListFragment: Fragment() {

    private lateinit var binding: FragmentDogBreedImagesListBinding
    private val viewModel: DogBreedImagesListViewModel by viewModels()

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DogBreedImagesAdapter {
            // TODO add navigation to screen 3
        }

        binding.dogBreedImages.layoutManager = LinearLayoutManager(requireContext())
        binding.dogBreedImages.adapter = adapter

        mDisposable.add(
            viewModel.getDogBreeds().subscribe {
                adapter.submitData(lifecycle, it)
            }
        )
    }

    override fun onDestroyView() {
        mDisposable.dispose()

        super.onDestroyView()
    }
}
