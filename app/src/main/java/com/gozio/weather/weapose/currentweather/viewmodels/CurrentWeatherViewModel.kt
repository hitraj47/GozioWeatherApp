package com.gozio.weather.weapose.currentweather.viewmodels

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewData
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewState
import com.gozio.weather.weapose.currentweather.utils.Constants
import com.gozio.weather.weapose.currentweather.utils.toBackground
import com.gozio.weather.weapose.currentweather.utils.toDateTime
import com.gozio.weather.weapose.network.models.ApiResponse
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse
import com.gozio.weather.weapose.network.repositories.OpenWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.TimeZone
import javax.inject.Inject


@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val repository: OpenWeatherRepository) : ViewModel() {

    private val _uiState = MutableLiveData<CurrentWeatherViewState>()
    val uiState: LiveData<CurrentWeatherViewState> = _uiState

    suspend fun retrieveCurrentWeatherByCoordinates(lat: String, lon: String) {
        showLoadingState()
        when (val response = repository.getWeatherByCoordinates(lat, lon)) {
            is ApiResponse.Success -> getCityNameForModel(response.responseModel, lat, lon)
            is ApiResponse.Error -> showErrorState()
        }
    }

    private suspend fun getCityNameForModel(
        weatherByCoordinatesResponse: WeatherByCoordinatesResponse?,
        lat: String,
        lon: String
    ) {
        weatherByCoordinatesResponse?.let {
            when (val result = repository.getCityFromCoordinates(lat, lon)) {
                is ApiResponse.Success -> {
                    val data = CurrentWeatherViewData(
                        city = result.responseModel,
                        maxTemp = it.main.tempMax.toString(),
                        minTemp = it.main.tempMin.toString(),
                        temp = it.main.temp.toString(),
                        weather = it.weather.first().description.capitalize(Locale.current),
                        sunRise = it.sys.sunrise.toDateTime(Constants.DateFormat.HH_mm),
                        wind = it.wind.speed.toString(),
                        humidity = it.main.humidity.toString(),
                        background = (it.weather.first().icon).toBackground()
                    )
                    showSuccessState(data)
                }
                is ApiResponse.Error -> showErrorState()
            }
        }
    }

    private fun showLoadingState() {
        _uiState.postValue(CurrentWeatherViewState.Loading)
    }

    private fun showErrorState() {
        _uiState.postValue(CurrentWeatherViewState.Error)
    }

    private fun showSuccessState(data: CurrentWeatherViewData) {
        _uiState.postValue(CurrentWeatherViewState.Success(currentWeather = data))
    }

}