package com.slmyldz.project1

import android.app.Application
import com.slmyldz.project1.di.appModule
import com.slmyldz.project1.di.mvvmModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            fragmentFactory()
            // modules
            modules(appModule+mvvmModule)
        }
    }

}