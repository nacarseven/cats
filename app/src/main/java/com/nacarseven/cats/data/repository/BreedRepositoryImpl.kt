package com.nacarseven.cats.data.repository

import com.nacarseven.cats.data.mapper.mapToDomainList
import com.nacarseven.cats.data.networkmanager.NetworkManager
import com.nacarseven.cats.data.networkmanager.NoConnectionException
import com.nacarseven.cats.data.remote.datasource.BreedDataSource
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.repository.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BreedRepositoryImpl(
    private val breedDataSource: BreedDataSource,
    private val networkManager: NetworkManager
) : BreedRepository {

    override fun getSeriesDetail(): Flow<List<Breed>> {
        return flow {
            try {
                if (networkManager.isConnected()) {
                    val result = breedDataSource.getBreedList()
                    if (result.isSuccessful) {
                        emit(
                            result.body()?.mapToDomainList()
                                ?: throw IllegalStateException("Body is nullable")
                        )
                    }
                } else {
                    throw NoConnectionException()
                }
            } catch (e: Exception) {
                throw e

            }
        }
    }
}