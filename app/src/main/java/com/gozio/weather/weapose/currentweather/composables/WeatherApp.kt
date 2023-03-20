package com.gozio.weather.weapose.currentweather.composables

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gozio.weather.weapose.currentweather.composables.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun WeatherApp(appState: WeatherAppState = rememberWeatherAppState()) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()

    SideEffect {
        if (!appState.isCustomDarkMode) {
            systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = !darkIcons)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) {
        AnimatedNavHost(
            navController = appState.controller,
            startDestination = NestedGraph.SPLASH.route,
        ) {
            splash(appState)

            home(appState)
        }
    }
}


