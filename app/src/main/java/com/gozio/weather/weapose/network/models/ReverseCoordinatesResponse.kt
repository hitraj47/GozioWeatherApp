package com.gozio.weather.weapose.network.models

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.gozio.weather.weapose.network.models.ReverseCoordinatesResponse.ReverseCoordinatesResponseItem

class ReverseCoordinatesResponse : ArrayList<ReverseCoordinatesResponseItem>() {
    @Keep
    data class ReverseCoordinatesResponseItem(
        @Expose
        val country: String,
        @Expose
        val lat: Double,
        @SerializedName("local_names")
        @Expose
        val localNames: LocalNames,
        @Expose
        val lon: Double,
        @Expose
        @SerializedName("name")
        val cityName: String,
        @Expose
        val state: String
    ) {
        @Keep
        data class LocalNames(
            @Expose
            val ar: String,
            @Expose
            val be: String,
            @Expose
            val bg: String,
            @Expose
            val bo: String,
            @Expose
            val ce: String,
            @Expose
            val el: String,
            @Expose
            val en: String,
            @Expose
            val eo: String,
            @Expose
            val fa: String,
            @Expose
            val gu: String,
            @Expose
            val he: String,
            @Expose
            val ht: String,
            @Expose
            val hy: String,
            @Expose
            val ja: String,
            @Expose
            val ka: String,
            @Expose
            val kn: String,
            @Expose
            val ko: String,
            @Expose
            val kw: String,
            @Expose
            val la: String,
            @Expose
            val lt: String,
            @Expose
            val mk: String,
            @Expose
            val ml: String,
            @Expose
            val mr: String,
            @Expose
            val my: String,
            @Expose
            val oc: String,
            @Expose
            val os: String,
            @Expose
            val pl: String,
            @Expose
            val ru: String,
            @Expose
            val sr: String,
            @Expose
            val ta: String,
            @Expose
            val te: String,
            @Expose
            val th: String,
            @Expose
            val uk: String,
            @Expose
            val ur: String,
            @Expose
            val yi: String,
            @Expose
            val zh: String
        )
    }
}