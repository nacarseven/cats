package com.nacarseven.cats.presentation.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.usecase.GetBreedListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val getBreedListUseCase: GetBreedListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _breedListAction = MutableSharedFlow<BreedListAction>()
    val breedListAction = _breedListAction.asSharedFlow()

    private val _breedListViewState = MutableStateFlow(BreedListViewState())
    val breedListViewState = _breedListViewState.asStateFlow()


    fun clickOnBreedItem(breed: Breed) {
        viewModelScope.launch {
            _breedListAction.emit(BreedListAction.GoToBreedDetail(breed))
        }
    }

    fun getBreedList() {
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
        _breedListViewState.value = _breedListViewState.value.copy(isErrorState = true)
        setLoading(false)
    }

    private fun setLoading(isLoading: Boolean) {
        _breedListViewState.value = _breedListViewState.value.copy(isLoading = isLoading)
    }

}