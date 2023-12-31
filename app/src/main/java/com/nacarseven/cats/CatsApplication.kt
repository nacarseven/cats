package com.nacarseven.cats

import android.app.Application
import com.nacarseven.cats.di.appModule
import com.nacarseven.network.networkModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class CatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CatsApplication)
            modules(networkModule, appModule)
        }
    }
}