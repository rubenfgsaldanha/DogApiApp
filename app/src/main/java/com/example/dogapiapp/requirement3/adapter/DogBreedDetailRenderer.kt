package com.example.dogapiapp.requirement3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.dogapiapp.R
import com.example.dogapiapp.common.extensions.hide
import com.example.dogapiapp.common.extensions.show
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
            setText(breedName, content.breedName, R.string.breed_name_text)
            setText(breedGroup, content.breedGroup, R.string.breed_group_text)
            setText(origin, content.origin, R.string.origin_text)
            setText(temperament, content.temperament, R.string.temperament_text)
        }
    }

    private fun setText(textView: TextView, textToShow: String?, @StringRes stringId: Int) {
        if (textToShow.isNullOrEmpty()) {
            textView.hide()
        } else {
            textView.text = getString(stringId).plus(textToShow)
            textView.show()
        }
    }
    private fun getString(stringId: Int) = context.resources.getString(stringId)
}