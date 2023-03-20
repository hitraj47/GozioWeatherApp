package com.gozio.weather.weapose.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class WeatherByCoordinatesResponse(
    @Expose
    val base: String,
    @Expose
    val clouds: Clouds,
    @Expose
    val cod: Int,
    @Expose
    val coord: Coord,
    @Expose
    val dt: Int,
    @Expose
    val id: Int,
    @Expose
    val main: Main,
    @Expose
    val name: String,
    @Expose
    val sys: Sys,
    @Expose
    val timezone: Int,
    @Expose
    val visibility: Int,
    @Expose
    val weather: List<Weather>,
    @Expose
    val wind: Wind
) {
    @Keep
    data class Clouds(
        @Expose
        val all: Int
    )

    @Keep
    data class Coord(
        @Expose
        val lat: Double,
        @Expose
        val lon: Double
    )

    @Keep
    data class Main(
        @SerializedName("feels_like")
        @Expose
        val feelsLike: Double,
        @Expose
        val humidity: Int,
        @Expose
        val pressure: Int,
        @Expose
        val temp: Double,
        @SerializedName("temp_max")
        @Expose
        val tempMax: Double,
        @SerializedName("temp_min")
        @Expose
        val tempMin: Double
    )

    @Keep
    data class Sys(
        @Expose
        val country: String,
        @Expose
        val id: Int,
        @Expose
        val sunrise: Long,
        @Expose
        val sunset: Int,
        @Expose
        val type: Int
    )

    @Keep
    data class Weather(
        @Expose
        val description: String,
        @Expose
        val icon: String,
        @Expose
        val id: Int,
        @Expose
        val main: String
    )

    @Keep
    data class Wind(
        @Expose
        val deg: Int,
        @Expose
        val gust: Double,
        @Expose
        val speed: Double
    )
}