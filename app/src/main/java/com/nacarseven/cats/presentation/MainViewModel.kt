package com.nacarseven.cats.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nacarseven.cats.domain.entities.Breed

class MainViewModel : ViewModel() {

    private val _mainAction by lazy { MutableLiveData<MainAction>() }
    val mainAction: LiveData<MainAction> = _mainAction

    init {
        MainAction.GoToBreedListScreen.sendAction()
    }

    fun clickOnBreedItem(breed: Breed) {

    }

    private fun MainAction.sendAction() {
        _mainAction.value = this
    }
}