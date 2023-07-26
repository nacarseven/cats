package com.nacarseven.cats.presentation

import com.nacarseven.cats.domain.entities.Breed

sealed class MainAction {
    object GoToBreedListScreen : MainAction()
    class GoToBreedDetail(val breed: Breed) : MainAction()
}
