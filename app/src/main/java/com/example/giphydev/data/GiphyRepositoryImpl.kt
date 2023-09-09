package com.example.giphydev.data

import com.example.giphydev.data.models.GifsDetail
import com.example.giphydev.domain.GiphyRepository
import com.example.giphydev.domain.models.Gifs
import kotlinx.coroutines.runBlocking

class GiphyRepositoryImpl(val giphyApi: GiphyApi): GiphyRepository {
    private val api_key: String = "Ktli4o48X9FnJCb9Npp7qEg8Df8B1zXM"
    private var aqi: Int = 25
    private var offset: Int = 0
    private var rating: String = "g"
    private var lang: String = "en"

    override fun getGifs(q: String): Gifs {
        val gifsDetail: GifsDetail
        runBlocking{
            gifsDetail = giphyApi.getGifs(
                q = q,
                api_key = api_key,
                aqi = aqi,
                offset = offset,
                rating = rating,
                lang = lang)
        }
        val gifs = gifsDetail.gifsDetailtoGifs()
        return gifs
    }
}