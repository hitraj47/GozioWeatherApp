package com.gozio.weather.weapose.currentweather.viewmodels

import com.gozio.weather.weapose.base.BaseTest
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewState
import com.gozio.weather.weapose.network.models.ApiResponse
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse
import com.gozio.weather.weapose.network.repositories.OpenWeatherRepository
import com.gozio.weather.weapose.utils.ResourceUtil
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CurrentWeatherViewModelTest : BaseTest() {

    private lateinit var  currentWeatherViewModel: CurrentWeatherViewModel

    @MockK
    private lateinit var openWeatherRepository: OpenWeatherRepository

    @RelaxedMockK
    private lateinit var successfulWeatherResponse: ApiResponse.Success<WeatherByCoordinatesResponse>

    @RelaxedMockK
    private lateinit var successfulCityResponse: ApiResponse.Success<String>

    @Before
    fun setUp() {
        currentWeatherViewModel = CurrentWeatherViewModel(openWeatherRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should retrieve weather by coordinates when api calls successful`() = runTest {
        val weatherByCoordinatesResponse = ResourceUtil()
            .getObjectFromJsonResourcesFile("weather_response_success.json", WeatherByCoordinatesResponse::class.java)

        coEvery {
            openWeatherRepository.getWeatherByCoordinates(any(), any())
        } returns successfulWeatherResponse

        every {
            successfulWeatherResponse.responseModel
        } returns weatherByCoordinatesResponse

        coEvery {
            openWeatherRepository.getCityFromCoordinates(any(), any())
        } returns successfulCityResponse

        every {
            successfulCityResponse.responseModel
        } returns "Atlanta"

        currentWeatherViewModel.retrieveCurrentWeatherByCoordinates("5", "-22")

        assert(currentWeatherViewModel.uiState.value is CurrentWeatherViewState.Success)

        coVerify {
            openWeatherRepository.getWeatherByCoordinates("5", "-22")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should set ui status to error when weather by coordinates api call fails`() = runTest {
        coEvery {
            openWeatherRepository.getWeatherByCoordinates(any(), any())
        } returns ApiResponse.Error

        currentWeatherViewModel.retrieveCurrentWeatherByCoordinates("", "")

        assert(currentWeatherViewModel.uiState.value is CurrentWeatherViewState.Error)

        coVerify {
            openWeatherRepository.getWeatherByCoordinates(any(), any())
            openWeatherRepository.getCityFromCoordinates(any(), any()) wasNot Called
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should set ui state to error when city api call fails`() = runTest {
        coEvery {
            openWeatherRepository.getWeatherByCoordinates(any(), any())
        } returns successfulWeatherResponse

        val weatherByCoordinatesResponse = ResourceUtil()
            .getObjectFromJsonResourcesFile("weather_response_success.json", WeatherByCoordinatesResponse::class.java)
        every {
            successfulWeatherResponse.responseModel
        } returns weatherByCoordinatesResponse

        coEvery {
            openWeatherRepository.getCityFromCoordinates(any(), any())
        } returns ApiResponse.Error

        currentWeatherViewModel.retrieveCurrentWeatherByCoordinates("", "")

        assert(currentWeatherViewModel.uiState.value is CurrentWeatherViewState.Error)

        coVerify {
            openWeatherRepository.getWeatherByCoordinates(any(), any())
            openWeatherRepository.getCityFromCoordinates(any(), any())
        }
    }

}