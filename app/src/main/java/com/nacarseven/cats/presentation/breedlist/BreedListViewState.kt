package com.nacarseven.cats.presentation.breedlist

import com.nacarseven.cats.domain.entities.Breed

data class BreedListViewState(
    val isLoading: Boolean = false,
    val breedList: List<Breed>? = null
)