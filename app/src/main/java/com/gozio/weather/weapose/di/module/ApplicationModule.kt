package com.gozio.weather.weapose.di.module

import com.gozio.weather.weapose.network.HttpClient
import com.gozio.weather.weapose.network.repositories.OpenWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOpenWeatherRepository(httpClient: HttpClient) = OpenWeatherRepository(httpClient)

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient()
}