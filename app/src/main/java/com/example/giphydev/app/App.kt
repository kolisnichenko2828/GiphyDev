package com.example.giphydev.app

import android.app.Application
import com.example.giphydev.di.AppComponent
import com.example.giphydev.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}