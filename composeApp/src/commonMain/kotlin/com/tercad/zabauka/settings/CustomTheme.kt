package com.tercad.zabauka.settings

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val CustomBlue = Color(0xFF0D47A1)
val CustomLightBlue = Color(0xFF1976D2)
val CustomDarkBlue = Color(0xFF0D47A1)
val CustomError = Color(0xFFD32F2F)

private val LightColorPalette = lightColors(
    primary = CustomBlue,
    primaryVariant = CustomDarkBlue,
    secondary = CustomLightBlue,
    error = CustomError
)

private val DarkColorPalette = darkColors(
    primary = CustomBlue,
    primaryVariant = CustomDarkBlue,
    secondary = CustomLightBlue,
    error = CustomError
)

@Composable
fun CustomTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        // typography = Typography,
        // shapes = Shapes,
        content = content
    )
}