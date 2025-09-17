package com.sargis.khlopuzyan.retrofitapi

import android.app.Application
import com.sargis.khlopuzyan.retrofitapi.di.dataModule
import com.sargis.khlopuzyan.retrofitapi.di.domainModule
import com.sargis.khlopuzyan.retrofitapi.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule + domainModule + presentationModule)
        }
    }
}