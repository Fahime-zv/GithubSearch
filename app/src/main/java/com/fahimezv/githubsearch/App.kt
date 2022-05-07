package com.fahimezv.githubsearch

import android.app.Application
import com.fahimezv.githubsearch.data.dataModule
import com.fahimezv.githubsearch.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Setup Koin
        GlobalContext.startKoin {
            androidLogger(Level.ERROR) // Koin Android Logger
            androidContext(this@App) // Inject Android context
            modules(dataModule, presentationModule) // Modules
        }
    }

}