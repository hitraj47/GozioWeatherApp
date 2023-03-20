package com.gozio.weather.weapose.splash.viewmodels

import androidx.lifecycle.ViewModel
import com.airbnb.lottie.compose.LottieAnimationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    fun animationCompleted(animationState: LottieAnimationState): Boolean =
        animationState.isAtEnd && animationState.isPlaying
}
