package com.neonusa.marketplace.util

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.neonusa.marketplace.core.di.appModule
import com.neonusa.marketplace.core.di.repositoryModule
import com.neonusa.marketplace.core.di.viewModelModule

// import koin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        startKoin{
            androidContext(this@MyApp)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}