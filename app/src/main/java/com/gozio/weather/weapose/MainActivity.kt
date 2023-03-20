package com.gozio.weather.weapose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.gozio.weather.weapose.common.theme.WeaposeTheme
import com.gozio.weather.weapose.currentweather.composables.WeatherApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WeaposeTheme {
                WeatherApp()
            }
        }
    }
}
