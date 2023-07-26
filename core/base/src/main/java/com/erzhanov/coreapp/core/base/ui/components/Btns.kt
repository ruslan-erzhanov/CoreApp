package com.erzhanov.coreapp.core.base.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erzhanov.coreapp.core.base.ui.theme.customColors
import com.erzhanov.coreapp.core.base.ui.theme.headlineXSmall

@Composable
fun HighlightedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.customColors.btnHighlightedBg,
            disabledContainerColor = MaterialTheme.customColors.btnHighlightedBgDisabled,
            contentColor = MaterialTheme.customColors.btnHighlightedFg,
            disabledContentColor = MaterialTheme.customColors.btnHighlightedFg,
        ),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(13.dp)
    ) {
        when {
            enabled -> Text(
                modifier = Modifier,
                text = text,
                style = MaterialTheme.typography.headlineXSmall
            )
            else -> CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color(0xFF848CBE)
            )
        }
    }
}

@Composable
fun AccentTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.customColors.labelBrand,
            disabledContentColor = MaterialTheme.customColors.labelBrand.copy(alpha = 0.38f)
        ),
        enabled = enabled,
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(13.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.headlineXSmall
        )
    }
}