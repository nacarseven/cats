package com.nacarseven.cats.data.remote.datasource

import com.nacarseven.cats.data.api.CatsApi
import com.nacarseven.cats.data.remote.model.BreedResponse
import retrofit2.Response

class BreedDataSourceImpl(private val api: CatsApi) : BreedDataSource {

    override suspend fun getBreedList(): Response<List<BreedResponse>> = api.getBreedsList()
}