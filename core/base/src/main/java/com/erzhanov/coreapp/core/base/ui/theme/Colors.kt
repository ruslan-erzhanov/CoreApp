package com.erzhanov.coreapp.core.base.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


//label
private val labelAdditionalLight = Color(0xFF4C5D99)
private val labelAdditionalDark = Color(0xFF4C5D99)

private val labelPrimaryLight = Color(0xFF171A2F)
private val labelPrimaryDark = Color(0xFFD0D4E9)

private val labelBrandLight = Color(0xFFF26F10)
private val labelBrandDark = Color(0xFFF26F10)

private val labelSecondaryLight = Color(0xFFD0D4E9)
private val labelSecondaryDark = Color(0xFFFFFFFF)

private val labelTertiaryLight = Color(0xFFC5C8DC)
private val labelTertiaryDark = Color(0xFF485085)

private val labelErrorLight = Color(0xFFFF5555)
private val labelErrorDark = Color(0xFFFF5555)

//bg
private val bgErrorLight = Color(0xFFFF5555)
private val bgErrorDark = Color(0xFFFF5555)

private val bgSuccessLight = Color(0xFF76D23D)
private val bgSuccessDark = Color(0xFF76D23D)

private val bgWarningLight = Color(0xFFFFC860)
private val bgWarningDark = Color(0xFFFFC860)

private val splashBgLight = listOf(
    Color(0xFFFFC860),
    Color(0xFFFFE0A5),
)
private val splashBgDark = listOf(
    Color(0xFFFFC860),
    Color(0xFFFFE0A5),
)

private val bgPrimaryLight = Color(0xFFFFFFFF)
private val bgPrimaryDark = Color(0xFF090E25)

private val bgBrandLight = Color(0xFFF26F10)
private val bgBrandDark = Color(0xFFF26F10)

private val bgSecondaryLight = Color(0xFFF1F4FA)
private val bgSecondaryDark = Color(0xFF1C2140)

private val bgTertiaryLight = Color(0xFFE1E7F4)
private val bgTertiaryDark = Color(0xFF272E57)

private val bgModalLight = Color(0xFFF6F7F9)
private val bgModalDark = Color(0xFF1C2140)

//icons
private val iconPrimaryLight = Color(0xFF171A2F)
private val iconPrimaryDark = Color(0xFFD0D4E9)

private val iconBtnLight = Color(0xFFF26F10)
private val iconBtnDark = Color(0xFFFFFFFF)

private val iconSecondaryLight = Color(0xFFD0D4E9)
private val iconSecondaryDark = Color(0xFFFFFFFF)

private val iconTertiaryLight = Color(0xFFC5C8DC)
private val iconTertiaryDark = Color(0xFF485085)

private val iconErrorLight = Color(0xFFFF5555)
private val iconErrorDark = Color(0xFFFF5555)

//cell
private val cellPrimaryLight = Color(0xFFF1F4FA)
private val cellPrimaryDark = Color(0xFF1C2140)

private val cellSecondaryLight = Color(0xFFDBDBDB)
private val cellSecondaryDark = Color(0xFF272E57)

private val cellDividerLight = Color(0x1A1C2140)
private val cellDividerDark = Color(0x4D171A2F)

private val cellPrimaryHighlightedLight = Color(0xFFE6ECF8)
private val cellPrimaryHighlightedDark = Color(0xFF161B39)

private val cellPrimarySelectedLight = Color(0xFF1C2140)
private val cellPrimarySelectedDark = Color(0xFFF26F10)

//primary btn
private val btnPrimaryBgLight = Color(0xFF1C2140)
private val btnPrimaryBgDark = Color(0xFFF26F10)

private val btnPrimaryBgPressedLight = Color(0xFF000928)
private val btnPrimaryBgPressedDark = Color(0xFFDA5C00)

private val btnPrimaryBgDisabledLight = Color(0xFF9A9EB4)
private val btnPrimaryBgDisabledDark = Color(0x4DF26F10)

private val btnPrimaryFgLight = Color(0xFFFFFFFF)
private val btnPrimaryFgDark = Color(0xFFFFFFFF)

private val btnPrimaryFgDisabledLight = Color(0xFFADB1CC)
private val btnPrimaryFgDisabledDark = Color(0x1AFFFFFF)

//secondary btn
private val btnSecondaryBgLight = Color(0xFFF1F4FA)
private val btnSecondaryBgDark = Color(0xFF1C2140)

private val btnSecondaryBgPressedLight = Color(0xFFE6ECF8)
private val btnSecondaryBgPressedDark = Color(0xFF161B39)

private val btnSecondaryBgDisabledLight = Color(0xFFF1F4FA)
private val btnSecondaryBgDisabledDark = Color(0xFF10152E)

private val btnSecondaryFgLight = Color(0xFF1C2140)
private val btnSecondaryFgDark = Color(0xFFA6ACCB)

private val btnSecondaryFgDisabledLight = Color(0xFFD1D8E4)
private val btnSecondaryFgDisabledDark = Color(0xFF202441)

private val btnHighlightedBgLight = Color(0xFFF26F10)
private val btnHighlightedBgDark = Color(0xFFF26F10)

private val btnHighlightedBgPressedLight = Color(0xFFDA5C00)
private val btnHighlightedBgPressedDark = Color(0xFFDA5C00)

private val btnHighlightedBgDisabledLight = Color(0x4DF26F10)
private val btnHighlightedBgDisabledDark = Color(0x4DF26F10)

private val btnHighlightedFgLight = Color(0xFFFFFFFF)
private val btnHighlightedFgDark = Color(0xFFFFFFFF)

private val btnHighlightedFgDisabledLight = Color(0x80FFFFFF)
private val btnHighlightedFgDisabledDark = Color(0x80FFFFFF)

private val btnTertiaryBgLight = Color(0x99FFFFFF)
private val btnTertiaryBgDark = Color(0x99090E25)

private val btnTertiaryFgLight = Color(0xFF171A2F)
private val btnTertiaryFgDark = Color(0xFFFFFFFF)

val DarkColorScheme = darkColorScheme(
    primary = btnPrimaryBgDark,
    onPrimary = btnPrimaryBgDark,
    primaryContainer = cellPrimaryDark,
    onPrimaryContainer = btnSecondaryFgDark,

    secondary = btnSecondaryBgDark,
    onSecondary = btnSecondaryFgDark,

    tertiary = btnTertiaryBgDark,
    onTertiary = btnTertiaryFgDark,

    background = bgPrimaryDark,
    onBackground = iconPrimaryDark,
)

val LightColorScheme = lightColorScheme(
    primary = btnPrimaryBgLight,
    onPrimary = btnPrimaryBgLight,
    primaryContainer = cellPrimaryLight,
    onPrimaryContainer = btnSecondaryFgLight,

    secondary = btnSecondaryBgLight,
    onSecondary = btnSecondaryFgLight,

    tertiary = btnTertiaryBgLight,
    onTertiary = btnTertiaryFgLight,

    background = bgPrimaryLight,
    onBackground = iconPrimaryLight,
)

@Immutable
data class CustomColorsPalette(
    val labelPrimary: Color = Color.Unspecified,
    val labelBrand: Color = Color.Unspecified,
    val labelSecondary: Color = Color.Unspecified,
    val labelTertiary: Color = Color.Unspecified,
    val labelError: Color = Color.Unspecified,
    val labelAdditional: Color = Color.Unspecified,

    val bgPrimary: Color = Color.Unspecified,
    val bgBrand: Color = Color.Unspecified,
    val bgSecondary: Color = Color.Unspecified,
    val bgTertiary: Color = Color.Unspecified,
    val bgModal: Color = Color.Unspecified,
    val bgError: Color = Color.Unspecified,
    val bgSuccess: Color = Color.Unspecified,
    val bgWarning: Color = Color.Unspecified,
    val splashBg: List<Color> = listOf(),

    val iconPrimary: Color = Color.Unspecified,
    val iconBtn: Color = Color.Unspecified,
    val iconSecondary: Color = Color.Unspecified,
    val iconTertiary: Color = Color.Unspecified,
    val iconError: Color = Color.Unspecified,

    val cellPrimary: Color = Color.Unspecified,
    val cellPrimaryHighlighted: Color = Color.Unspecified,
    val cellPrimarySelected: Color = Color.Unspecified,
    val cellSecondary: Color = Color.Unspecified,
    val cellDivider: Color = Color.Unspecified,

    val btnPrimaryBg: Color = Color.Unspecified,
    val btnPrimaryBgPressed: Color = Color.Unspecified,
    val btnPrimaryBgDisabled: Color = Color.Unspecified,
    val btnPrimaryFg: Color = Color.Unspecified,
    val btnPrimaryFgDisabled: Color = Color.Unspecified,

    val btnSecondaryBg: Color = Color.Unspecified,
    val btnSecondaryBgPressed: Color = Color.Unspecified,
    val btnSecondaryBgDisabled: Color = Color.Unspecified,
    val btnSecondaryFg: Color = Color.Unspecified,
    val btnSecondaryFgDisabled: Color = Color.Unspecified,

    val btnHighlightedBg: Color = Color.Unspecified,
    val btnHighlightedBgPressed: Color = Color.Unspecified,
    val btnHighlightedBgDisabled: Color = Color.Unspecified,
    val btnHighlightedFg: Color = Color.Unspecified,
    val btnHighlightedFgDisabled: Color = Color.Unspecified,
)

val LightCustomColorsPalette = CustomColorsPalette(
    labelPrimary = labelPrimaryLight,
    labelBrand = labelBrandLight,
    labelSecondary = labelSecondaryLight,
    labelTertiary = labelTertiaryLight,
    labelError = labelErrorLight,
    labelAdditional = labelAdditionalLight,

    bgPrimary = bgPrimaryLight,
    bgBrand = bgBrandLight,
    bgSecondary = bgSecondaryLight,
    bgTertiary = bgTertiaryLight,
    bgModal = bgModalLight,
    bgError = bgErrorLight,
    bgSuccess = bgSuccessLight,
    bgWarning = bgWarningLight,
    splashBg = splashBgLight,

    iconPrimary = iconPrimaryLight,
    iconBtn = iconBtnLight,
    iconSecondary = iconSecondaryLight,
    iconTertiary = iconTertiaryLight,
    iconError = iconErrorLight,

    cellPrimary = cellPrimaryLight,
    cellPrimaryHighlighted = cellPrimaryHighlightedLight,
    cellPrimarySelected = cellPrimarySelectedLight,
    cellSecondary = cellSecondaryLight,
    cellDivider = cellDividerLight,

    btnPrimaryBg = btnPrimaryBgLight,
    btnPrimaryBgPressed = btnPrimaryBgPressedLight,
    btnPrimaryBgDisabled = btnPrimaryBgDisabledLight,
    btnPrimaryFg = btnPrimaryFgLight,
    btnPrimaryFgDisabled = btnPrimaryFgDisabledLight,

    btnSecondaryBg = btnSecondaryBgLight,
    btnSecondaryBgPressed = btnSecondaryBgPressedLight,
    btnSecondaryBgDisabled = btnSecondaryBgDisabledLight,
    btnSecondaryFg = btnSecondaryFgLight,
    btnSecondaryFgDisabled = btnSecondaryFgDisabledLight,

    btnHighlightedBg = btnHighlightedBgLight,
    btnHighlightedBgPressed = btnHighlightedBgPressedLight,
    btnHighlightedBgDisabled = btnHighlightedBgDisabledLight,
    btnHighlightedFg = btnHighlightedFgLight,
    btnHighlightedFgDisabled = btnHighlightedFgDisabledLight,
)

val DarkCustomColorsPalette = CustomColorsPalette(
    labelPrimary = labelPrimaryDark,
    labelBrand = labelBrandDark,
    labelSecondary = labelSecondaryDark,
    labelTertiary = labelTertiaryDark,
    labelError = labelErrorDark,
    labelAdditional = labelAdditionalDark,

    bgPrimary = bgPrimaryDark,
    bgBrand = bgBrandDark,
    bgSecondary = bgSecondaryDark,
    bgTertiary = bgTertiaryDark,
    bgModal = bgModalDark,
    bgError = bgErrorDark,
    bgSuccess = bgSuccessDark,
    bgWarning = bgWarningDark,
    splashBg = splashBgDark,

    iconPrimary = iconPrimaryDark,
    iconBtn = iconBtnDark,
    iconSecondary = iconSecondaryDark,
    iconTertiary = iconTertiaryDark,
    iconError = iconErrorDark,

    cellPrimary = cellPrimaryDark,
    cellPrimaryHighlighted = cellPrimaryHighlightedDark,
    cellPrimarySelected = cellPrimarySelectedDark,
    cellSecondary = cellSecondaryDark,
    cellDivider = cellDividerDark,

    btnPrimaryBg = btnPrimaryBgDark,
    btnPrimaryBgPressed = btnPrimaryBgPressedDark,
    btnPrimaryBgDisabled = btnPrimaryBgDisabledDark,
    btnPrimaryFg = btnPrimaryFgDark,
    btnPrimaryFgDisabled = btnPrimaryFgDisabledDark,

    btnSecondaryBg = btnSecondaryBgDark,
    btnSecondaryBgPressed = btnSecondaryBgPressedDark,
    btnSecondaryBgDisabled = btnSecondaryBgDisabledDark,
    btnSecondaryFg = btnSecondaryFgDark,
    btnSecondaryFgDisabled = btnSecondaryFgDisabledDark,

    btnHighlightedBg = btnHighlightedBgDark,
    btnHighlightedBgPressed = btnHighlightedBgPressedDark,
    btnHighlightedBgDisabled = btnHighlightedBgDisabledDark,
    btnHighlightedFg = btnHighlightedFgDark,
    btnHighlightedFgDisabled = btnHighlightedFgDisabledDark,
)

val LocalCustomColors = staticCompositionLocalOf { CustomColorsPalette() }

val MaterialTheme.customColors: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current