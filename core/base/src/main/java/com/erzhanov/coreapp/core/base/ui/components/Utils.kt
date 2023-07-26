package com.erzhanov.coreapp.core.base.ui.components

import android.graphics.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
inline fun <reified T> Flow<T>.bindWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collect(action)
        }
    }
}

fun <T> MutableState<T>.mutate(block: T.() -> T) {
    value = value.block()
}

enum class CustomPosition {
    BOTTOM, TOP
}

fun Modifier.customPositionModifier(pos: CustomPosition) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(constraints.maxWidth, constraints.maxHeight) {
        when (pos) {
            CustomPosition.BOTTOM -> {
                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
            }

            CustomPosition.TOP -> {
                placeable.place(0, 0, 10f)
            }
        }
    }
}

fun Dp.toPx(density: Density) = density.run { this@toPx.toPx() }

fun TextUnit.toPx(density: Density) = density.run { this@toPx.toPx() }

@OptIn(ExperimentalUnitApi::class)
fun TextUnit.toEm(): TextUnit {
    return if (isSp) {
        TextUnit(value = this.value * 0.0624F, type = TextUnitType.Em)
    } else {
        this
    }
}

/**
 * Save current canvas' state, do [action] and restore state
 *
 * @param action action that will be executed after saving canvas' state
 */
fun Canvas.withSaving(action: Canvas.() -> Unit) {
    save()
    action(this)
    restore()
}

/**
 * A side effect of composition that must run for any new unique value of [keys].
 *
 * @param keys unique values that triggers side effect to relaunch when it changes
 * @param effect A side effect of composition
 */
@Composable
@NonRestartableComposable
fun SideKeyEffect(
    vararg keys: Any?,
    effect: () -> Unit,
) {
    remember(*keys) { effect.also { it() } }
}
