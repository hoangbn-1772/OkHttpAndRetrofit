package com.sun.okhttp_retrofit.ui

import android.app.Application
import com.sun.okhttp_retrofit.di.networkModule
import com.sun.okhttp_retrofit.di.repositoryModule
import com.sun.okhttp_retrofit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, networkModule, repositoryModule))
        }
    }
}
