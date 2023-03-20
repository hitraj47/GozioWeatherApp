package com.gozio.weather.weapose.currentweather.data

import com.gozio.weather.weapose.currentweather.data.models.Model
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse

interface DataModelMapper<M : Model, VD : ViewData> {
    fun mapToModel(viewData: VD): M

    fun mapToViewData(model: WeatherByCoordinatesResponse): VD
}
