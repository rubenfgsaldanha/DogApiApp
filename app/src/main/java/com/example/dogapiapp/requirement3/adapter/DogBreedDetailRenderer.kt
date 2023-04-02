package com.example.dogapiapp.requirement3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogapiapp.R
import com.example.dogapiapp.common.extensions.setText
import com.example.dogapiapp.databinding.DogBreedDetailItemBinding
import com.example.dogapiapp.requirement3.model.DogBreedDetailUiModel
import com.pedrogomez.renderers.Renderer

class DogBreedDetailRenderer: Renderer<DogBreedDetailUiModel>() {

    private lateinit var binding: DogBreedDetailItemBinding

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
        binding = DogBreedDetailItemBinding.inflate(inflater, parent, false)
        return binding.root
    }

    override fun render() {
        with(binding) {
            breedName.setText(content.breedName, R.string.breed_name_text)
            breedGroup.setText(content.breedGroup, R.string.breed_group_text)
            origin.setText(content.origin, R.string.origin_text)
            temperament.setText(content.temperament, R.string.temperament_text)
        }
    }
}