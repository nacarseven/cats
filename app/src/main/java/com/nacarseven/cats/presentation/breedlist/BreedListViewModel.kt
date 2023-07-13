package com.nacarseven.cats.presentation.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.usecase.GetBreedListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val getBreedListUseCase: GetBreedListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _breedListAction = MutableStateFlow<BreedListAction?>(null)
    val breedListAction: StateFlow<BreedListAction?> = _breedListAction

    private val _breedListViewState = MutableStateFlow(BreedListViewState())
    val breedListViewState: StateFlow<BreedListViewState> = _breedListViewState

    init {
        getBreedList()
    }

    fun clickOnBreedItem(breed: Breed) {
        _breedListAction.value = BreedListAction.GoToBreedDetail(breed)
    }

    private fun getBreedList() {
        viewModelScope.launch {
            getBreedListUseCase()
                .flowOn(dispatcher)
                .onStart { setLoading(true) }
                .catch { setErrorState() }
                .collect { list ->
                    _breedListViewState.value =
                        _breedListViewState.value.copy(isLoading = false, breedList = list)
                }
        }
    }

    private fun setErrorState() {
        setLoading(false)
        _breedListViewState.value = _breedListViewState.value.copy(isErrorState = true)
    }

    private fun setLoading(isLoading: Boolean) {
        _breedListViewState.value = _breedListViewState.value.copy(isLoading = isLoading)
    }

}