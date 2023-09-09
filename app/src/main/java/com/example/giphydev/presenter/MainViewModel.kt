package com.example.giphydev.presenter

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphydev.domain.models.Gifs
import com.example.giphydev.domain.usecases.GetGifsUsecase
import kotlinx.coroutines.runBlocking

class MainViewModel(val getGifsUsecase: GetGifsUsecase): ViewModel() {
    val liveDataGiphy = MutableLiveData<Gifs>()
    val liveDataFragment = MutableLiveData<String>()
    var lastSearch = "hello"
    var lastPosition = 0
    var position: Int = 0

    fun get(q: String) {
        liveDataGiphy.value = getGifsUsecase.execute(q)
    }

    fun changeFragmentTo(fragment: String) {
        liveDataFragment.value = fragment
    }
}