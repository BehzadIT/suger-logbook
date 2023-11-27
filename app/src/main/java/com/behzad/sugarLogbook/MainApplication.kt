package com.behzad.sugarLogbook

import android.app.Application
import com.behzad.sugarLogbook.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                Modules.app,
                Modules.useCases,
                Modules.viewModels,
                Modules.database,
                Modules.dataStore
            )
        }
    }
}