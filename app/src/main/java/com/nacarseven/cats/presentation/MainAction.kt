package com.nacarseven.cats.presentation

sealed class MainAction {
    object GoToBreedListScreen : MainAction()
    object GoToBreedDetail : MainAction()
}
