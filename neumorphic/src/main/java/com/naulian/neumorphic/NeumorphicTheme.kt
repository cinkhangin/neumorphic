package com.naulian.neumorphic

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class NeumorphicColorScheme(
    val background: Color,
    val light: Color,
    val onColorLight: Color,
    val shadow: Color,
    val onColorShadow: Color,
    val container: Color
)

fun lightNeumorphicColorScheme(
    background: Color = LightBackground,
    light: Color = Light,
    shadow: Color = Shadow,
    onColorLight: Color = OnColorLight,
    onColorShadow: Color = OnColorShadow,
    container: Color = LightContainer
) = NeumorphicColorScheme(
    background = background,
    light = light,
    shadow = shadow,
    onColorLight = onColorLight,
    onColorShadow = onColorShadow,
    container = container
)

fun darkNeumorphicColorScheme(
    background: Color = DarkBackground,
    light: Color = DarkLight,
    shadow: Color = DarkShadow,
    onColorLight: Color = DarkOnColorLight,
    onColorShadow: Color = DarkOnColorShadow,
    container: Color = DarkContainer
) = NeumorphicColorScheme(
    background = background,
    light = light,
    shadow = shadow,
    onColorLight = onColorLight,
    onColorShadow = onColorShadow,
    container = container
)

@Composable
fun NeumorphicTheme(
    neumorphicColorScheme: NeumorphicColorScheme = NeumorphicTheme.colorScheme,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    shapes: Shapes = MaterialTheme.shapes,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNeumorphicColorScheme provides neumorphicColorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
}

object NeumorphicTheme {
    val colorScheme: NeumorphicColorScheme
        @Composable @ReadOnlyComposable get() = LocalNeumorphicColorScheme.current
}

internal val LocalNeumorphicColorScheme = staticCompositionLocalOf { lightNeumorphicColorScheme() }