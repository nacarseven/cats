package com.nacarseven.cats.presentation.breedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _breedListAction by lazy { MutableLiveData<BreedListAction>() }
    val breedListAction: LiveData<BreedListAction> = _breedListAction

    private val _breedListViewState = MutableStateFlow(BreedListViewState())
    val breedListViewState: StateFlow<BreedListViewState> = _breedListViewState

    init {
        getBreedList()
    }

    private fun getBreedList() {
        viewModelScope.launch {
            getBreedListUseCase()
                .flowOn(dispatcher)
                .onStart { setLoading(true) }
                .catch { setLoading(false) }
                .collect { list ->
                    _breedListViewState.value =
                        _breedListViewState.value.copy(isLoading = false, breedList = list)
                }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _breedListViewState.value = _breedListViewState.value.copy(isLoading = isLoading)
    }

}