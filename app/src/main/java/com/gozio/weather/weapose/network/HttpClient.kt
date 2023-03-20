package com.gozio.weather.weapose.network

import com.gozio.weather.weapose.BuildConfig
import com.gozio.weather.weapose.network.api.OpenWeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getOpenWeatherService(): OpenWeatherApi = retrofit.create(OpenWeatherApi::class.java)

    companion object OpenWeatherApiConstants {
        const val BASE_URL = "https://api.openweathermap.org/"
        const val OPENWEATHER_API_KEY = BuildConfig.OPENWEATHER_API_KEY
    }
}