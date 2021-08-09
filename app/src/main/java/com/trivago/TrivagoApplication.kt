package com.trivago

import android.app.Application
import com.trivago.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrivagoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TrivagoApplication)
            modules(dataModule)
        }
    }
}

/*
* Favorites character of star wars
*
*
* */