package com.example.giphydev.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphydev.domain.models.Gifs
import com.example.giphydev.domain.usecases.GetGifsUsecase
import kotlinx.coroutines.launch

class MainViewModel(val getGifsUsecase: GetGifsUsecase): ViewModel() {
    val liveDataGiphy = MutableLiveData<Gifs>()
    val liveDataFragment = MutableLiveData<String>()
    var lastSearch = "hello"
    var lastPosition = 0

    fun get(q: String) {
        viewModelScope.launch {
            liveDataGiphy.value = getGifsUsecase.execute(q)
        }
    }

    fun changeFragmentTo(fragment: String) {
        liveDataFragment.value = fragment
    }
}