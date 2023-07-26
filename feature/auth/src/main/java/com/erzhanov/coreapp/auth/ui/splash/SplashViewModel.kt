package com.erzhanov.coreapp.auth.ui.splash

import com.erzhanov.coreapp.auth.model.SplashEffect
import com.erzhanov.coreapp.auth.model.SplashIntent
import com.erzhanov.coreapp.auth.model.SplashState
import com.erzhanov.coreapp.core.base.ui.MviViewModel
import com.erzhanov.coreapp.data.auth.AuthUseCase
import com.erzhanov.coreapp.data.auth.TokensUseCase
import com.erzhanov.coreapp.data.common.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val tokensUseCase: TokensUseCase,
    private val authUseCase: AuthUseCase,
    private val logger: Logger,
) : MviViewModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {

    override fun onError(error: Throwable) {
        logger.log("${error.message}")
    }

    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.CheckSession -> {
                checkSession()
            }

            SplashIntent.SignUp -> {
                launch {
                    reduceState {
                        SplashState(isInProgress = true)
                    }
                    authUseCase.signUp()
                    emitUiEffect(SplashEffect.ToMain)
                }
            }
        }
    }

    private fun checkSession() {
        launch {
            when (tokensUseCase.getToken()) {
                null ->
                    emitUiEffect(SplashEffect.ToGetStarted)
                else ->
                    emitUiEffect(SplashEffect.ToMain)
            }
        }
    }
}