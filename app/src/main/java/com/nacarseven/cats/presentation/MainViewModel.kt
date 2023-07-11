package com.nacarseven.cats.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _mainAction by lazy { MutableLiveData<MainAction>() }
    val mainAction: LiveData<MainAction> = _mainAction

    init {
        MainAction.GoToBreedListScreen.sendAction()
    }

    private fun MainAction.sendAction() {
        _mainAction.value = this
    }
}