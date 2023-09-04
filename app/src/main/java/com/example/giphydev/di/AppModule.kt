package com.example.giphydev.di

import com.example.giphydev.domain.usecases.GetGifsUsecase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(getGifsUsecase: GetGifsUsecase): MainViewModelFactory {
        return MainViewModelFactory(getGifsUsecase)
    }
}