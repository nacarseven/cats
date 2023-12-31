package com.nacarseven.cats.data.remote.datasource

import com.nacarseven.cats.data.remote.model.BreedResponse
import retrofit2.Response

interface BreedRemoteDataSource {

    suspend fun getBreedList(): Response<List<BreedResponse>>
}