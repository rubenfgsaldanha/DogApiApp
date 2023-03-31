package com.example.dogapiapp.requirement1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapiapp.R
import com.example.dogapiapp.databinding.FragmentDogBreedImagesListBinding
import com.example.dogapiapp.requirement1.adapter.DogBreedImagesAdapter
import com.example.dogapiapp.requirement1.adapter.GRID_LAYOUT
import com.example.dogapiapp.requirement1.adapter.LINEAR_LAYOUT
import com.example.dogapiapp.requirement1.presentation.DogBreedImagesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class DogBreedImagesListFragment: Fragment() {

    private lateinit var binding: FragmentDogBreedImagesListBinding
    private val viewModel: DogBreedImagesListViewModel by viewModels()

    private val mDisposable = CompositeDisposable()
    private lateinit var adapter: DogBreedImagesAdapter

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

        adapter = DogBreedImagesAdapter(LINEAR_LAYOUT) {
            // TODO add navigation to screen 3
        }

        updateAdapterAndLayoutType(LINEAR_LAYOUT)
        binding.dogBreedImages.adapter = adapter

        mDisposable.add(
            viewModel.getDogBreeds().subscribe {
                adapter.submitData(lifecycle, it)
            }
        )

        setupClickListeners()
    }

    private fun updateAdapterAndLayoutType(layoutType: Int) {
        // TODO improve this to survive configuration changes
        binding.dogBreedImages.layoutManager = if (layoutType == LINEAR_LAYOUT) {
            LinearLayoutManager(requireContext())
        } else {
            val numCells = requireContext().resources.getInteger(R.integer.grid_num_cells)
            GridLayoutManager(requireContext(), numCells)
        }

        adapter.layoutType = layoutType
    }

    private fun setupClickListeners() {
        binding.buttonLinear.setOnClickListener {
            updateAdapterAndLayoutType(LINEAR_LAYOUT)
        }

        binding.buttonGrid.setOnClickListener {
            updateAdapterAndLayoutType(GRID_LAYOUT)
        }
    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }
}
