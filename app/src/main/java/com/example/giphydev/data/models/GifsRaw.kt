package com.example.giphydev.data.models

import com.example.giphydev.domain.models.Gifs

data class Data(val images: Images)
data class Images(val downsized_large: DownsizedLarge)
data class DownsizedLarge(val url: String)

data class GifsRaw(val `data`: List<Data>) {
    fun gifsRawToGifs(): Gifs {
        val urls = data.map { it.images.downsized_large.url }

        return Gifs(urls)
    }
}