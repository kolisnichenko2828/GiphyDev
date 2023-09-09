package com.example.giphydev.domain

import com.example.giphydev.domain.models.Gifs

interface GiphyRepository {

    suspend fun getGifs(q: String): Gifs
}