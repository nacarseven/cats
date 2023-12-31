package com.nacarseven.cats.domain.repository

import com.nacarseven.cats.domain.entities.Breed
import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    fun getBreedList(): Flow<List<Breed>>
}