package com.nechvolod.konstantin.kernelapp

import android.app.Application
import com.nechvolod.konstantin.kernelapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class KernelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()

        startKoin {
            androidContext(this@KernelApplication)
            androidLogger()
            modules(appModule)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
        Timber.tag("CML")
    }
}