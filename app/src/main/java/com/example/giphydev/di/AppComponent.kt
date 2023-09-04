package com.example.giphydev.di

import com.example.giphydev.presenter.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun injectMainActivity(activity: MainActivity)
    fun getMainViewModelFactory(): MainViewModelFactory
}