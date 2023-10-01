package com.example.giphydev.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphydev.domain.models.Gifs
import com.example.giphydev.domain.usecases.GetGifsUsecase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel(val getGifsUsecase: GetGifsUsecase) : ViewModel() {

    val liveDataGifs = MutableLiveData<Gifs>()

    fun getGifs(q: String) {
        viewModelScope.launch {
            liveDataGifs.value = getGifsUsecase.execute(q)
        }
    }
}