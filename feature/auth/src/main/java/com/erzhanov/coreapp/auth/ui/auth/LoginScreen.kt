package com.erzhanov.coreapp.auth.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.erzhanov.coreapp.auth.ui.splashBgModifier
import com.erzhanov.coreapp.core.base.ui.theme.customColors

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().splashBgModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.clickable(onClick = onClick),
            text = "LOGIN",
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.customColors.labelPrimary)
        )
        Text(
            modifier = Modifier.clickable(onClick = onSignUpClick),
            text = "Sign Up",
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.customColors.labelPrimary)
        )
        Text(
            modifier = Modifier.clickable(onClick = onForgotClick),
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.customColors.labelPrimary)
        )
    }
}

@Composable
@Preview
private fun LoginContentPreview() {
    LoginScreen(
        onClick = {},
        onSignUpClick = {},
        onForgotClick = {}
    )
}