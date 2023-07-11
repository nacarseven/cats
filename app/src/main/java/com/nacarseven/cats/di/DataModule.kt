@file:OptIn(ExperimentalSerializationApi::class)

package com.nacarseven.cats.di

import com.nacarseven.cats.data.api.CatsApi
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSource
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSourceImpl
import com.nacarseven.cats.data.repository.BreedRepositoryImpl
import com.nacarseven.cats.domain.repository.BreedRepository
import com.nacarseven.cats.domain.usecase.GetBreedListUseCase
import com.nacarseven.cats.presentation.MainViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    data()
    domain()
    presentation()
}

private fun Module.data() {
    factory<CatsApi> { get<Retrofit>().create(CatsApi::class.java) }
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
    factory { GetBreedListUseCase(repository = get()) }
}

private fun Module.presentation() {
    viewModel { MainViewModel() }
}

