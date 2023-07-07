@file:OptIn(ExperimentalSerializationApi::class)

package com.nacarseven.cats.di

import com.nacarseven.cats.builder.RetrofitBuilderImpl
import com.nacarseven.cats.data.api.CatsApi
import com.nacarseven.cats.data.networkmanager.NetworkManager
import com.nacarseven.cats.data.networkmanager.NetworkManagerImpl
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSource
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSourceImpl
import com.nacarseven.cats.data.repository.BreedRepositoryImpl
import com.nacarseven.cats.domain.repository.BreedRepository
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    foundation()
    data()
    presentation()
}

private fun Module.foundation() {
    factory<NetworkManager> { NetworkManagerImpl(androidApplication()) }
    single {
        val timeout = 15L
        RetrofitBuilderImpl(isDebug = BuildConfig.DEBUG, timeout = timeout).getClient()
    }
    factory<CatsApi> { get<Retrofit>().create(CatsApi::class.java) }

}

private fun Module.data() {
    factory<BreedRemoteDataSource> {
        BreedRemoteDataSourceImpl(api = get())
    }
    factory<BreedRepository> {
        BreedRepositoryImpl(
            networkManager = get(),
            breedDataSource = get()
        )
    }
}

private fun Module.domain() {

}

private fun Module.presentation() {

}

