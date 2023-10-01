package com.example.giphydev.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphydev.domain.usecases.GetGifsUsecase
import com.example.giphydev.presenter.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val getGifsUsecase: GetGifsUsecase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getGifsUsecase = getGifsUsecase) as T
    }
}