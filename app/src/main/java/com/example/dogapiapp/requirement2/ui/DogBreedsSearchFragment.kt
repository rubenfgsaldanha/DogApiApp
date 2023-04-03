package com.example.dogapiapp.requirement2.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dogapiapp.databinding.FragmentDogBreedSearchBinding
import com.example.dogapiapp.requirement2.adapter.DogBreedSearchResultsAdapter
import com.example.dogapiapp.requirement2.presentation.DogBreedSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class DogBreedsSearchFragment: Fragment() {

    private lateinit var binding: FragmentDogBreedSearchBinding
    private val viewModel: DogBreedSearchViewModel by viewModels()

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchBar()
    }

    private fun setupSearchBar() {
        binding.searchBar.editText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                hideKeyboard()
                true
            }
            else{
                false
            }
        }

        binding.searchBar.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                (binding.searchResults.adapter as? DogBreedSearchResultsAdapter)?.filter?.filter(text.toString())
            }

        })
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchBar.editText.windowToken, 0)
    }

    override fun onStart() {
        super.onStart()

        mDisposable.add(
            viewModel.allDogBreeds.subscribe(
                { dogBreads ->
                    binding.searchResults.adapter = DogBreedSearchResultsAdapter(
                        totalDogBreeds = dogBreads,
                        currentFilteredResults = viewModel.currentFilteredResults,
                        performSearch = { text, list ->
                            viewModel.performSearch(text, list)
                        },
                        onItemClick = {
                            findNavController().navigate(
                                DogBreedsSearchFragmentDirections.actionDogBreedsSearchFragmentToDogBreedDetailFragment(dogBreedId = it)
                            )
                        }
                    )
                },
                { error ->
                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    override fun onStop() {
        mDisposable.clear()
        super.onStop()
    }
}