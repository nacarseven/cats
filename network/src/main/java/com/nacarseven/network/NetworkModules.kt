@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.nacarseven.network

import com.nacarseven.network.builder.RetrofitBuilderImpl
import com.nacarseven.network.networkmanager.NetworkManager
import com.nacarseven.network.networkmanager.NetworkManagerImpl
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {
    factory<NetworkManager> { NetworkManagerImpl(androidApplication()) }
    single {
        val timeout = 15L
        RetrofitBuilderImpl(isDebug = BuildConfig.DEBUG, timeout = timeout).getClient()
    }
}