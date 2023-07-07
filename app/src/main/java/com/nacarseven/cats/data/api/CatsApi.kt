package com.nacarseven.cats.data.api

import com.nacarseven.cats.data.remote.model.BreedResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatsApi {

    @GET("breeds")
    suspend fun getBreedsList(): Response<List<BreedResponse>>
}