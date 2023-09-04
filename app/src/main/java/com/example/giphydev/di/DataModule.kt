package com.example.giphydev.di

import com.example.giphydev.data.GiphyApi
import com.example.giphydev.data.GiphyRepositoryImpl
import com.example.giphydev.domain.GiphyRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class DataModule {
    private val baseUrl = "https://api.giphy.com/v1/gifs/"

    @Provides
    fun provideGiphyApi(): GiphyApi {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient as OkHttpClient)
            .build()
        val service = retrofit.create(GiphyApi::class.java)

        return service
    }

    @Provides
    fun provideGiphyRepository(giphyApi: GiphyApi): GiphyRepository {
        return GiphyRepositoryImpl(giphyApi)
    }
}