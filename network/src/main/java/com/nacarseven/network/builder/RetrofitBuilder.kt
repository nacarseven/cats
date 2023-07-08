package com.example.network.builder

import retrofit2.Retrofit

interface RetrofitBuilder {
    fun getClient(): Retrofit
}
