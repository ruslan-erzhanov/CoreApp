package com.erzhanov.coreapp.auth.model

import com.erzhanov.coreapp.core.base.ui.model.UiEffect
import com.erzhanov.coreapp.core.base.ui.model.UiIntent
import com.erzhanov.coreapp.core.base.ui.model.UiState

data class SplashState(
    val isInProgress: Boolean = false
) : UiState

sealed class SplashIntent: UiIntent {
    object CheckSession : SplashIntent()
    object SignUp : SplashIntent()
}

sealed class SplashEffect: UiEffect {

    object ToGetStarted : SplashEffect()
    object ToMain : SplashEffect()
}