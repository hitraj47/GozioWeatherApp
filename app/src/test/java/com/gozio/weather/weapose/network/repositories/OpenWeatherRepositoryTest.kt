package com.gozio.weather.weapose.network.repositories

import com.gozio.weather.weapose.base.BaseTest
import com.gozio.weather.weapose.network.HttpClient
import com.gozio.weather.weapose.network.api.OpenWeatherApi
import com.gozio.weather.weapose.network.models.ApiResponse
import com.gozio.weather.weapose.network.models.ReverseCoordinatesResponse
import com.gozio.weather.weapose.network.models.WeatherByCoordinatesResponse
import com.gozio.weather.weapose.utils.ResourceUtil
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class OpenWeatherRepositoryTest : BaseTest() {

    @MockK
    private lateinit var httpClient: HttpClient

    @MockK
    private lateinit var openWeatherApi: OpenWeatherApi

    private lateinit var openWeatherRepository: OpenWeatherRepository

    @Before
    fun setUp() {
        every {
            httpClient.getOpenWeatherService()
        } returns openWeatherApi

        openWeatherRepository = OpenWeatherRepository(httpClient)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return response model when get weather by coordinates api call is successful`() = runTest {
        val weatherResponseObject = ResourceUtil().getObjectFromJsonResourcesFile(
            "weather_response_success.json",
            WeatherByCoordinatesResponse::class.java
        )
        coEvery {
            openWeatherApi.getWeatherByCoordinates(any(), any())
        } returns Response.success(weatherResponseObject)

        val result = openWeatherRepository.getWeatherByCoordinates("", "")

        assert(result is ApiResponse.Success)

        coVerify {
            httpClient.getOpenWeatherService()
            openWeatherApi.getWeatherByCoordinates(any(), any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return error model when get weather by coordinates api call fails`() = runTest {
        coEvery {
            openWeatherApi.getWeatherByCoordinates(any(), any())
        } returns Response.error(400, "error".toResponseBody())

        val result = openWeatherRepository.getWeatherByCoordinates("", "")

        assert(result is ApiResponse.Error)

        coVerify {
            httpClient.getOpenWeatherService()
            openWeatherApi.getWeatherByCoordinates(any(), any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return city when get city by coordinates api call is successful`() = runTest {
        val reverseCoordinatesResponse = ResourceUtil().getObjectFromJsonResourcesFile(
            "reverse_coordinates_response_success.json",
            ReverseCoordinatesResponse::class.java
        )
        coEvery {
            openWeatherApi.reverseCoordinatesLookup(any(), any())
        } returns Response.success(reverseCoordinatesResponse)

        val result = openWeatherRepository.getCityFromCoordinates("", "")

        assert(result is ApiResponse.Success)
        assertEquals("Atlanta", (result as ApiResponse.Success<String>).responseModel)

        coVerify {
            httpClient.getOpenWeatherService()
            openWeatherApi.reverseCoordinatesLookup(any(), any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return error model when get city by coordinates api call fails`() = runTest {
        coEvery {
            openWeatherApi.reverseCoordinatesLookup(any(), any())
        } returns Response.error(400, "error".toResponseBody())

        val result = openWeatherRepository.getCityFromCoordinates("", "")

        assert(result is ApiResponse.Error)

        coVerify {
            httpClient.getOpenWeatherService()
            openWeatherApi.reverseCoordinatesLookup(any(), any())
        }
    }
}