package com.example.giphydev.di

import com.example.giphydev.domain.GiphyRepository
import com.example.giphydev.domain.usecases.GetGifsUsecase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetGifsUsecase(giphyRepository: GiphyRepository): GetGifsUsecase {
        return GetGifsUsecase(giphyRepository)
    }
}