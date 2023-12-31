package com.nacarseven.cats.presentation.breedlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nacarseven.cats.R
import com.nacarseven.cats.databinding.FragmentBreedListBinding
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.presentation.MainViewModel
import com.nacarseven.cats.presentation.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreedListFragment : Fragment(R.layout.fragment_breed_list) {

    private val viewModel: BreedListViewModel by viewModel()
    private val sharedViewModel: MainViewModel by sharedViewModel()
    private val binding: FragmentBreedListBinding by viewBinding()
    private val seriesListAdapter = BreedlListAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        handleState()
        handleAction()
        viewModel.getBreedList()
    }

    private fun handleAction() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.breedListAction.collect {
                if (it is BreedListAction.GoToBreedDetail) {
                    sharedViewModel.clickOnBreedItem(it.breedItem)
                }
            }
        }
    }

    private fun handleState() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.breedListViewState.collect { state ->
                    seriesListAdapter.submitList(state.breedList)
                    setLoading(state.isLoading)
                    setErrorMessage(state.isErrorState)
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun setErrorMessage(isErrorState: Boolean){
        binding.tvErrorMessage.isVisible = isErrorState
    }

    private fun setupRecyclerView() {
        with(binding.rcvBreeds) {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = seriesListAdapter
        }
    }

    private fun onItemClick(breed: Breed) {
        viewModel.clickOnBreedItem(breed)
    }
}