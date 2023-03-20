package com.gozio.weather.weapose.network.models

sealed class ApiResponse<out T> {
    object Error : ApiResponse<Nothing>()
    data class Success<T>(val responseModel: T) : ApiResponse<T>()
}