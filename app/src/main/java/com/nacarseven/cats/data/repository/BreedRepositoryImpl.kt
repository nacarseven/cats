package com.nacarseven.cats.data.repository

import com.nacarseven.cats.data.mapper.mapToDomainList
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSource
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.repository.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BreedRepositoryImpl(
    private val breedDataSource: BreedRemoteDataSource
) : BreedRepository {

    override fun getBreedList(): Flow<List<Breed>> {
        return flow {
            try {
                val result = breedDataSource.getBreedList()
                if (result.isSuccessful) {
                    emit(
                        result.body()?.mapToDomainList()
                            ?: throw IllegalStateException("Body is nullable")
                    )
                }
            } catch (e: Exception) {
                throw e

            }
        }
    }
}