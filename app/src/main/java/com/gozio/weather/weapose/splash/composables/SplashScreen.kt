package com.gozio.weather.weapose.splash.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gozio.weather.weapose.R
import com.gozio.weather.weapose.splash.viewmodels.SplashViewModel
import com.gozio.weather.weapose.common.theme.WeaposeTheme
import com.gozio.weather.weapose.currentweather.composables.WeatherAppState

@Composable
fun Splash(
    appState: WeatherAppState,
) {
    SplashScreen(
        onNavigateHome = appState::navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateHome: () -> Unit = {}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo_animation))
    val logoAnimationState = animateLottieCompositionAsState(composition = composition)

    if (viewModel.animationCompleted(logoAnimationState)) {
        LaunchedEffect(true) {
            onNavigateHome.invoke()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LottieAnimation(modifier = Modifier.align(Alignment.Center),
                composition = composition,
                progress = { logoAnimationState.progress })
        }
    }
}

@Preview
@Composable
fun Splash_Preview() {
    WeaposeTheme {
        SplashScreen()
    }
}
