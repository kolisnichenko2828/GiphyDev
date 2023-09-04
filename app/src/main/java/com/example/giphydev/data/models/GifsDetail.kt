package com.example.giphydev.data.models

import com.example.giphydev.domain.models.Gifs

data class GifsDetail(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination) {

    fun gifsDetailtoGifs(): Gifs {
        val urls = MutableList<String>(25) { "" }
        var i = 0
        for (gif in data) {
            urls[i] = gif.images.downsized_large.url
            i = i + 1
        }
        return Gifs(urls)
    }
}