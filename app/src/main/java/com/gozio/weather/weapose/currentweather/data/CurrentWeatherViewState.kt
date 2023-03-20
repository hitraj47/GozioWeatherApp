package com.gozio.weather.weapose.currentweather.data

sealed interface CurrentWeatherViewState {
    object Loading : CurrentWeatherViewState
    data class Success(
        val currentWeather: CurrentWeatherViewData
    ) : CurrentWeatherViewState
    object Error: CurrentWeatherViewState
}