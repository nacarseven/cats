@file:OptIn(ExperimentalSerializationApi::class)

package com.nacarseven.cats.data.di

import com.nacarseven.cats.builder.RetrofitBuilderImpl
import com.nacarseven.cats.data.networkmanager.NetworkManager
import com.nacarseven.cats.data.networkmanager.NetworkManagerImpl
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

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

}

private fun Module.data() {

}

private fun Module.presentation() {

}

