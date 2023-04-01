package com.example.dogapiapp.requirement2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapiapp.R
import com.example.dogapiapp.common.extensions.setText
import com.example.dogapiapp.databinding.DogBreedSearchItemBinding
import com.example.dogapiapp.requirement2.model.DogBreedSearchUiModel

class DogBreedSearchResultsAdapter(
    private var totalDogBreeds: List<DogBreedSearchUiModel>,
    private var currentFilteredResults: List<DogBreedSearchUiModel>,
    private var performSearch: (CharSequence, List<DogBreedSearchUiModel>) -> List<DogBreedSearchUiModel>,
    private val onItemClick: (Int) -> Unit,
): RecyclerView.Adapter<DogBreedSearchResultsAdapter.DogBreedSearchViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedSearchViewHolder {
        return DogBreedSearchViewHolder(
            DogBreedSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DogBreedSearchViewHolder, position: Int) {
        return holder.bind(currentFilteredResults[position])
    }

    override fun getItemCount() = currentFilteredResults.size

    override fun getFilter() = dogBreedFilter

    private val dogBreedFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            results.values = performSearch(constraint, totalDogBreeds)
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            currentFilteredResults = listOf()
            currentFilteredResults = results.values as List<DogBreedSearchUiModel>
            notifyDataSetChanged()
        }

    }

    inner class DogBreedSearchViewHolder(
        private val binding: DogBreedSearchItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DogBreedSearchUiModel) {
            with(binding) {
                breedName.setText(item.breedName, R.string.breed_name_text)
                breedGroup.setText(item.breedGroup, R.string.breed_group_text)
                origin.setText(item.origin, R.string.origin_text)
            }

            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }
}