package com.gozio.weather.weapose.currentweather.composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.gozio.weather.weapose.splash.composables.Splash
import kotlinx.coroutines.CoroutineScope

sealed class Screen(val route: String) {
    object Splash : Screen("splash")

    object CurrentWeather : Screen("current_weather")
}

enum class NestedGraph(val route: String) {
    HOME(route = "home_nav"), SPLASH(route = "splash_nav"),
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splash(appState: WeatherAppState) {
    navigation(
        route = NestedGraph.SPLASH.route,
        startDestination = Screen.Splash.route,
    ) {
        composable(
            route = Screen.Splash.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
        ) {
            Splash(
                appState = appState,
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home(appState: WeatherAppState) {
    navigation(
        route = NestedGraph.HOME.route,
        startDestination = Screen.CurrentWeather.route,
    ) {
        composable(
            route = Screen.CurrentWeather.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
        ) {
            CurrentWeather(
                appState = appState
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberWeatherAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    controller: NavHostController = rememberAnimatedNavController(),
): WeatherAppState = remember(coroutineScope, controller) {
    WeatherAppState(coroutineScope, controller)
}

class WeatherAppState(
    private val coroutineScope: CoroutineScope,
    val controller: NavHostController,
) {
    val isCustomDarkMode: Boolean
        get() {
            return false
        }

    fun navigateToHome() {
        controller.navigate(route = Screen.CurrentWeather.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
}
