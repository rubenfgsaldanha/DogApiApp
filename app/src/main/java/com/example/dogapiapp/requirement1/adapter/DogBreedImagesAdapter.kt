package com.example.dogapiapp.requirement1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dogapiapp.databinding.DogBreedImageItemBinding
import com.example.dogapiapp.requirement1.model.DogBreedUiModel

class DogBreedImagesAdapter(
    private val onItemClick: (Int) -> Unit,
): PagingDataAdapter<DogBreedUiModel, DogBreedImagesAdapter.DogBreedImagesViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DogBreedUiModel>() {
            override fun areItemsTheSame(oldItem: DogBreedUiModel, newItem: DogBreedUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DogBreedUiModel, newItem: DogBreedUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogBreedImagesAdapter.DogBreedImagesViewHolder {

        return DogBreedImagesViewHolder(
            DogBreedImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: DogBreedImagesAdapter.DogBreedImagesViewHolder, position: Int) = holder.bind(getItem(position))


    inner class DogBreedImagesViewHolder(
        private val binding: DogBreedImageItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: DogBreedUiModel?){
            item?.let {
                binding.breedName.text = item.name

                binding.root.setOnClickListener { onItemClick(item.id) }

                item.imageUrl?.let {
                    loadImage(item.imageUrl)
                }
            }
        }

        private fun loadImage(url: String){
            Glide.with(binding.dogBreedImage.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.dogBreedImage)
        }
    }
}