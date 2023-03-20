package com.gozio.weather.weapose.network.api

import com.gozio.weather.weapose.network.HttpClient
import com.gozio.weather.weapose.network.models.ReverseCoordinatesResponse
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String = HttpClient.OPENWEATHER_API_KEY,
        @Query("units") units: String = "imperial"
    ): Response<WeatherByCoordinatesResponse>

    @GET("geo/1.0/reverse")
    suspend fun reverseCoordinatesLookup(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String = HttpClient.OPENWEATHER_API_KEY
    ): Response<ReverseCoordinatesResponse>
}