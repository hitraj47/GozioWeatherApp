package com.gozio.weather.weapose.network.repositories

import com.gozio.weather.weapose.network.HttpClient
import com.gozio.weather.weapose.network.models.ApiResponse
import com.gozio.weather.weapose.network.models.ApiResponse.Error
import com.gozio.weather.weapose.network.models.ApiResponse.Success
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getWeatherByCoordinates(lat: String, lon: String): ApiResponse<WeatherByCoordinatesResponse?> {
        val response = httpClient.getOpenWeatherService().getWeatherByCoordinates(lat, lon)
        return if (response.isSuccessful) {
            Success(responseModel = response.body())
        } else {
            Error
        }
    }

    suspend fun getCityFromCoordinates(lat: String, lon: String): ApiResponse<String> {
        val response = httpClient.getOpenWeatherService().reverseCoordinatesLookup(lat, lon)
        return if (response.isSuccessful) {
            val body = response.body()
            val city = body?.first()?.cityName.orEmpty()
            Success(responseModel = city)
        } else {
            Error
        }
    }
}