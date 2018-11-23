package com.tlgbltcn.app.weather

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.tlgbltcn.app.weather.di.component.DaggerApplicationComponent
import com.tlgbltcn.app.weather.di.module.ApplicationModule

class App : MultiDexApplication() {

    val component by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}

