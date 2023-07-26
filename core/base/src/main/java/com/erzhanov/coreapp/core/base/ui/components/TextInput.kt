package com.erzhanov.coreapp.core.base.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.erzhanov.coreapp.core.base.R
import com.erzhanov.coreapp.core.base.ui.theme.customColors
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SimpleInput(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    keyboardType: KeyboardType = KeyboardType.Text,
    hint: String = "",
    error: String = "",
    nextFocus: FocusDirection? = null,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
            ,
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(MaterialTheme.customColors.cellSecondary),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            val onAction = {
                focusManager.clearFocus()
                nextFocus?.let(focusManager::moveFocus)?: focusManager.clearFocus()
            }
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                val (text, clear) = createRefs()
                androidx.compose.animation.AnimatedVisibility(
                    visible = value.isNotEmpty(),
                    enter = fadeIn() + scaleIn(),
                    exit = scaleOut() + fadeOut(),
                    modifier = Modifier
                        .constrainAs(clear) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                            height = Dimension.fillToConstraints
                        }
                    ,
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            onValueChange("")
                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_close_16),
                            contentDescription = "Localized description",
                            tint = MaterialTheme.customColors.iconTertiary
                        )
                    }
                }
                val customTextSelectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.customColors.labelPrimary,
                    backgroundColor = MaterialTheme.customColors.labelPrimary.copy(alpha = 0.3f)
                )
                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    TextField(
                        modifier = Modifier
                            .constrainAs(text) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(clear.start)
                                width = Dimension.fillToConstraints
                            }
                        ,
                        value = value,
                        onValueChange = onValueChange,
                        visualTransformation = VisualTransformation.None.takeUnless { keyboardType.isPassword } ?: PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = imeAction,
                            capitalization = capitalization,
                            keyboardType = keyboardType,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onAction() },
                            onGo = { onAction() },
                            onNext = { onAction() },
                            onSend = { onAction() },
                            onSearch = { onAction() },
                            onPrevious = { onAction() },
                        ),
                        maxLines = 1,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.customColors.labelPrimary),
                        placeholder = {
                            Text(text = hint, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.customColors.labelTertiary))
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.customColors.labelPrimary,
                            focusedTextColor = MaterialTheme.customColors.labelPrimary,
                            unfocusedTextColor = MaterialTheme.customColors.labelPrimary,
                            disabledTextColor = MaterialTheme.customColors.labelPrimary,
                            errorTextColor = MaterialTheme.customColors.labelPrimary,
                        )
                    )
                }
            }
        }
        androidx.compose.animation.AnimatedVisibility(
            visible = error.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp),
                text = error,
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.customColors.labelError),
            )
        }
    }
}

val KeyboardType.isPassword: Boolean
    get() = this == KeyboardType.Password || this == KeyboardType.NumberPassword

@Composable
@Preview
private fun SimpleInputPreview() {
    SimpleInput(
        value = "SimpleInputPreview"
    )
}