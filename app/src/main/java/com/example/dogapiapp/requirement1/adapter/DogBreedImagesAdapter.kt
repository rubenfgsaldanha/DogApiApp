package com.example.dogapiapp.requirement1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dogapiapp.databinding.DogBreedImageItemGridBinding
import com.example.dogapiapp.databinding.DogBreedImageItemLinearBinding
import com.example.dogapiapp.requirement1.model.DogBreedUiModel

const val LINEAR_LAYOUT = 1
const val GRID_LAYOUT = 2

class DogBreedImagesAdapter(
    var layoutType: Int,
    private val onItemClick: (Int) -> Unit,
): PagingDataAdapter<DogBreedUiModel, RecyclerView.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DogBreedUiModel>() {
            override fun areItemsTheSame(oldItem: DogBreedUiModel, newItem: DogBreedUiModel): Boolean {
                return oldItem.id == newItem.id && oldItem.sortOrder == newItem.sortOrder
            }

            override fun areContentsTheSame(oldItem: DogBreedUiModel, newItem: DogBreedUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (viewType == LINEAR_LAYOUT) {
            DogBreedImagesLinearViewHolder(
                DogBreedImageItemLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            DogBreedImagesGridViewHolder(
                DogBreedImageItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (layoutType == LINEAR_LAYOUT) {
            (holder as DogBreedImagesLinearViewHolder).bind(getItem(position))
        } else {
            (holder as DogBreedImagesGridViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return layoutType
    }

    inner class DogBreedImagesLinearViewHolder(
        private val binding: DogBreedImageItemLinearBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: DogBreedUiModel?){
            item?.let {
                binding.breedName.text = item.name

                binding.root.setOnClickListener { onItemClick(item.id) }

                item.imageUrl?.let {
                    loadImage(binding.dogBreedImage, item.imageUrl)
                }
            }
        }
    }

    inner class DogBreedImagesGridViewHolder(
        private val binding: DogBreedImageItemGridBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: DogBreedUiModel?){
            item?.let {
                binding.breedNameGrid.text = item.name

                binding.root.setOnClickListener { onItemClick(item.id) }

                item.imageUrl?.let {
                    loadImage(binding.dogBreedImageGrid, item.imageUrl)
                }
            }
        }
    }

    private fun loadImage(imageView: ImageView, url: String){
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }
}