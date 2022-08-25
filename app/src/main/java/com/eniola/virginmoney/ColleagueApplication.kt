package com.eniola.virginmoney

import android.app.Application
import com.eniola.virginmoney.dependencyInjection.networkModule
import com.eniola.virginmoney.dependencyInjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ColleagueApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, networkModule))
        }
    }
}