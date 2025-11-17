package com.example.rekha.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,

    secondary = PrimaryRed,
    onSecondary = Color.White,

    surface = SurfaceBright,
    surfaceDim = SurfaceDim,
    onSurface = TextPrimary,

    outline = DividerColor
)


@Composable
fun HoldingsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}