package com.gozio.weather.weapose.currentweather.data.models

import com.google.gson.annotations.SerializedName

data class Cloud(
    @SerializedName("all") val all: Int? = 0,
) : Model()
