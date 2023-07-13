package com.nacarseven.cats.presentation.breedlist

import com.nacarseven.cats.domain.entities.Breed

sealed class BreedListAction {
    class GoToBreedDetail(val breedItem: Breed) : BreedListAction()
}
