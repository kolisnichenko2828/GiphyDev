package com.example.giphydev.domain.usecases

import com.example.giphydev.domain.GiphyRepository
import com.example.giphydev.domain.models.Gifs

class GetGifsUsecase(private val giphyRepository: GiphyRepository) {

    fun execute(q: String): Gifs {
        val gifs = giphyRepository.getGifs(q = q)
        return gifs
    }
}