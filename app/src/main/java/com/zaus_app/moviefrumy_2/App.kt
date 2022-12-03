package com.zaus_app.moviefrumy_2

import android.app.Application
import com.zaus_app.moviefrumy_2.di.AppComponent
import com.zaus_app.moviefrumy_2.di.DaggerAppComponent


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}