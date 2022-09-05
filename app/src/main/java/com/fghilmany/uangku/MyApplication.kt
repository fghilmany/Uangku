package com.fghilmany.uangku

import android.app.Application
import com.fghilmany.uangku.core.di.databaseModule
import com.fghilmany.uangku.core.di.networkModule
import com.fghilmany.uangku.core.di.repositoryModule
import com.fghilmany.uangku.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}