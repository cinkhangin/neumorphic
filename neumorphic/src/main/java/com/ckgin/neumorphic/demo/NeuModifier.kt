package com.ckgin.neumorphic.demo

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicTheme
import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin


@Composable
fun Modifier.neuBackground(
    shape: Shape
): Modifier = background(
    color = NeumorphicTheme.colorScheme.background,
    shape = shape
)

@Composable
fun Modifier.neuDownLight(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    light: Color = NeumorphicTheme.colorScheme.light,
): Modifier = innerShadow(
    shape = shape,
    shadow = Shadow(
        offset = DpOffset(
            x = offset, y = -offset
        ),
        radius = radius,
        color = light,
    )
)

@Composable
fun Modifier.neuDownShadow(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    shadow: Color = NeumorphicTheme.colorScheme.shadow,
): Modifier = innerShadow(
    shape = shape,
    shadow = Shadow(
        offset = DpOffset(
            x = -offset, y = offset
        ),
        radius = radius,
        color = shadow,
    )
)


@Composable
fun Modifier.neuUpLight(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    light: Color = NeumorphicTheme.colorScheme.light
): Modifier = innerShadow(
    shape = shape,
    shadow = Shadow(
        offset = DpOffset(
            x = -offset, y = offset
        ),
        radius = radius,
        color = light,
    )
)

@Composable
fun Modifier.neuUpShadow(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    shadow: Color = NeumorphicTheme.colorScheme.shadow
): Modifier = innerShadow(
    shape = shape,
    shadow = Shadow(
        offset = DpOffset(
            x = offset, y = -offset
        ),
        radius = radius,
        color = shadow,
    )
)

@Composable
fun Modifier.neuEdgeDown(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    light: Color = NeumorphicTheme.colorScheme.light,
    shadow: Color = NeumorphicTheme.colorScheme.shadow,
): Modifier = this
    .neuDownLight(
        shape = shape, radius = radius, offset = offset, light = light
    )
    .neuDownShadow(
        shape = shape, radius = radius, offset = offset, shadow = shadow
    )

@Composable
fun Modifier.neuEdgeUp(
    shape: Shape,
    radius: Dp = 1.dp,
    offset: Dp = 1.dp,
    light: Color = NeumorphicTheme.colorScheme.light,
    shadow: Color = NeumorphicTheme.colorScheme.shadow,
): Modifier = this
    .neuUpLight(
        shape = shape, radius = radius, offset = offset, light = light
    )
    .neuUpShadow(
        shape = shape, radius = radius, offset = offset, shadow = shadow
    )


fun Color.shadow(factor: Float = 0.2f): Color {
    val hsv = FloatArray(3)
    AndroidColor.colorToHSV(this.toArgb(), hsv)
    hsv[2] = (hsv[2] - factor).coerceIn(0f, 1f)
    return Color.hsv(hsv[0], hsv[1], hsv[2], alpha = this.alpha)
}


fun Color.light(factor: Float = 0.2f): Color {
    val hsv = FloatArray(3)
    AndroidColor.colorToHSV(this.toArgb(), hsv)
    hsv[2] = (hsv[2] + factor).coerceIn(0f, 1f)
    return Color.hsv(hsv[0], hsv[1], hsv[2], alpha = this.alpha)
}


class SquarcleShape(private val n: Float = 4f) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val rx = size.width / 2f
            val ry = size.height / 2f

            for (i in 0..360) {
                val angle = Math.toRadians(i.toDouble())
                val cosA = cos(angle)
                val sinA = sin(angle)

                val x = rx * abs(cosA).pow(2.0 / n) * sign(cosA)
                val y = ry * abs(sinA).pow(2.0 / n) * sign(sinA)

                if (i == 0) {
                    moveTo((rx + x).toFloat(), (ry + y).toFloat())
                } else {
                    lineTo((rx + x).toFloat(), (ry + y).toFloat())
                }
            }
            close()
        }
        return Outline.Generic(path)
    }
}