package com.example.giphydev.data

import com.example.giphydev.data.models.GifsRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi{
    @GET("search?")
    suspend fun getGifsRaw(
        @Query("api_key") api_key: String,
        @Query("q") q: String,
        @Query("limit") aqi: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String): GifsRaw
}