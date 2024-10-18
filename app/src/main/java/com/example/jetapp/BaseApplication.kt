package com.example.jetapp

import android.app.Application
import com.example.jetapp.di.appModule
import com.example.jetapp.di.databaseModule
import com.example.jetapp.di.networkModule
import com.example.jetapp.di.repositoryModule
import com.example.jetapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    appModule,
                    viewModelModule
                )
            )
        }
    }
}