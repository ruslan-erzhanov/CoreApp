package com.erzhanov.coreapp.auth.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.erzhanov.coreapp.auth.model.SplashEffect
import com.erzhanov.coreapp.auth.model.SplashIntent
import com.erzhanov.coreapp.auth.ui.SplashView
import com.erzhanov.coreapp.core.base.ui.components.bindWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun SplashScreen(
    effects: Flow<SplashEffect>,
    intents: (SplashIntent) -> Unit,
    goToStart: () -> Unit,
    goToMain: () -> Unit,
) {
    SplashView(boxContent = {
        effects.bindWithLifecycle {
            when(it) {
                SplashEffect.ToGetStarted -> goToStart()
                SplashEffect.ToMain -> goToMain()
            }
        }
        LaunchedEffect(key1 = true) {
            intents(SplashIntent.CheckSession)
        }
    })
}