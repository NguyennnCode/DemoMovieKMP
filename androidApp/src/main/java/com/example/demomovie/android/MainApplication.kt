package com.example.demomovie.android

import android.app.Application
import com.example.demomovie.android.di.androidModule
import com.example.demomovie.di.shareModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(shareModule(), androidModule())
        }
    }
}