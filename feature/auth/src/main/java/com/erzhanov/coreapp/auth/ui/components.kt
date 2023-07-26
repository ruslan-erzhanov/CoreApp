package com.erzhanov.coreapp.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.erzhanov.coreapp.core.base.ui.theme.customColors

@Composable
fun SplashView(
    boxContent: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier.splashBgModifier,
        content = boxContent
    )
}

val Modifier.splashBgModifier: Modifier
    @Composable
    @ReadOnlyComposable
    get() = fillMaxSize()
            .navigationBarsPadding()
            .background(
                brush = Brush.linearGradient(
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f),
                    colors = MaterialTheme.customColors.splashBg
                )
            )

@Composable
@Preview
private fun PreviewSplashView() {
    SplashView {

    }
}