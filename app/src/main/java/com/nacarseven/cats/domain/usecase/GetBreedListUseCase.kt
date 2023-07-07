package com.nacarseven.cats.domain.usecase

import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.repository.BreedRepository
import kotlinx.coroutines.flow.Flow

class GetBreedListUseCase(
    private val repository: BreedRepository
) {
    operator fun invoke(): Flow<List<Breed>> = repository.getBreedList()
}