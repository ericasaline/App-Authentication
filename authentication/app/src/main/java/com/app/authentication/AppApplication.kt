package com.app.authentication

import android.app.Application
import com.app.authentication.di.dataModule
import com.app.authentication.di.databaseModule
import com.app.authentication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, dataModule, databaseModule)
        }
    }
}