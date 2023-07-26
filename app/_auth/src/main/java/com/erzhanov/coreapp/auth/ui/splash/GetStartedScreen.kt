package com.erzhanov.coreapp.auth.ui.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erzhanov.coreapp.auth.model.SplashEffect
import com.erzhanov.coreapp.auth.model.SplashIntent
import com.erzhanov.coreapp.auth.model.SplashState
import com.erzhanov.coreapp.auth.ui.SplashView
import com.erzhanov.coreapp.core.base.R
import com.erzhanov.coreapp.core.base.ui.components.AccentTextButton
import com.erzhanov.coreapp.core.base.ui.components.HighlightedButton
import com.erzhanov.coreapp.core.base.ui.components.bindWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun GetStartedScreen(
    state: SplashState,
    effects: Flow<SplashEffect>,
    intents: (SplashIntent) -> Unit,
    goAuth: () -> Unit,
    goToMain: () -> Unit,
) {
    effects.bindWithLifecycle {
        when (it) {
            SplashEffect.ToGetStarted -> Unit
            SplashEffect.ToMain -> goToMain()
        }
    }
    GetStartedView(
        state = state,
        intents = intents,
        goAuth = goAuth
    )
}

@Composable
fun GetStartedView(
    state: SplashState,
    intents: (SplashIntent) -> Unit,
    goAuth: () -> Unit,
) {
    SplashView(
        boxContent = {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .alpha(1f.takeUnless { state.isInProgress } ?: .5f)
                    .fillMaxWidth()
            ) {
                HighlightedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        intents(SplashIntent.SignUp)
                    },
                    enabled = state.isInProgress.not(),
                    text = stringResource(R.string.get_started),
                )
                AccentTextButton(
                    onClick = goAuth,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp),
                    enabled = state.isInProgress.not(),
                    text = stringResource(R.string.already_have_account),
                )
            }
        })
}

@Composable
@Preview
private fun PreviewGetStartedView() {
    GetStartedView(
        state = SplashState(isInProgress = false),
        intents = { },
        goAuth = { }
    )
}