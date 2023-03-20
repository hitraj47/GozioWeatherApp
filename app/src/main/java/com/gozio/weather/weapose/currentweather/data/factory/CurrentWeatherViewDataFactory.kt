package com.gozio.weather.weapose.currentweather.data.factory

import com.gozio.weather.weapose.R
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewData

fun previewCurrentWeatherViewData() = CurrentWeatherViewData(
    city = "[city]",
    maxTemp = "0",
    minTemp = "0",
    temp = "0",
    weather = "[weather]",
    sunRise = "00:00",
    wind = "0",
    humidity = "0",
    background = R.drawable.bg_clear_sky
)
