package com.nacarseven.cats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nacarseven.cats.domain.entities.Breed
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainAction = MutableSharedFlow<MainAction>()
    val mainAction = _mainAction.asSharedFlow()

    init {
        setAction(MainAction.GoToBreedListScreen)
    }

    fun clickOnBreedItem(breed: Breed) {
        setAction(MainAction.GoToBreedDetail(breed))
    }

    private fun setAction(action: MainAction) {
        viewModelScope.launch {
            _mainAction.emit(action)
        }
    }
}