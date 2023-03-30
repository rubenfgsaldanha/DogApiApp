package com.example.dogapiapp.requirement1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dogapiapp.databinding.FragmentDogBreedImagesListBinding
import com.example.dogapiapp.requirement1.presentation.DogBreedImagesListViewModel

class DogBreedImagesListFragment: Fragment() {

    private lateinit var binding: FragmentDogBreedImagesListBinding
    private val viewModel: DogBreedImagesListViewModel by viewModels()

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

        //Observers
    }
}
