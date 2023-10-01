package com.example.giphydev.app

import android.app.Application
import com.example.giphydev.di.AppComponent
import com.example.giphydev.di.DaggerAppComponent

class App: Application() {
    companion object {
        val appComponent: AppComponent = DaggerAppComponent.create()
    }
}