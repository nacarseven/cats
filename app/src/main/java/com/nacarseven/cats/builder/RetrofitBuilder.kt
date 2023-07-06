package com.nacarseven.cats.builder

import retrofit2.Retrofit

interface RetrofitBuilder {
    fun getClient(): Retrofit
}
