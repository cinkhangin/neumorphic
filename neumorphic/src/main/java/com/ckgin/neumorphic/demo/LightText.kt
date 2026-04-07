package com.ckgin.neumorphic.demo

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicTheme

@Composable
fun LightSwitchDemo(
    modifier: Modifier = Modifier
) {
    var isOn by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0E5EC)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(56.dp)
        ) {
            ArrowIcon(
                direction = ArrowDirection.Left,
                size = 40.dp,
                color = Color.Black.copy(alpha = 0.85f),
                strokeWidth = 3.dp
            )

            LightSwitch(
                isOn = isOn,
                onToggle = { isOn = !isOn }
            )

            ArrowIcon(
                direction = ArrowDirection.Right,
                size = 40.dp,
                color = Color.Black.copy(alpha = 0.85f),
                strokeWidth = 3.dp
            )
        }
    }
}

@Composable
fun LightSwitch(
    isOn: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val thumbOffset by animateDpAsState(
        targetValue = if (isOn) 110.dp else 0.dp,
        animationSpec = tween(durationMillis = 400),
        label = "thumbOffset"
    )
    val glowAlpha by animateFloatAsState(
        targetValue = if (isOn) 1f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "glowAlpha"
    )

    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        // External leakage glow
        if (isOn) {
            Box(
                modifier = Modifier
                    .offset(x = 60.dp)
                    .size(130.dp, 150.dp)
                    .alpha(glowAlpha * 0.3f)
                    .blur(40.dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFFFA000), Color.Transparent)
                        )
                    )
            )
        }

        // The Base (Outer Capsule)
        Box(
            modifier = Modifier
                .width(260.dp)
                .height(130.dp)
                .neumorphicRaised(
                    shape = RoundedCornerShape(65.dp),
                    elevation = 14.dp,
                    darkColor = Color.Black.copy(alpha = 0.12f)
                )
                .background(Color(0xFFE0E5EC), RoundedCornerShape(65.dp)),
            contentAlignment = Alignment.Center
        ) {
            // The Track (Inner Recessed part)
            Box(
                modifier = Modifier
                    .width(230.dp)
                    .height(100.dp)
                    .innerShadow(
                        shape = RoundedCornerShape(50.dp),
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.14f),
                            offset = DpOffset(4.dp, 4.dp),
                            radius = 10.dp
                        )
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(50.dp),
                        shadow = Shadow(
                            color = Color.White.copy(alpha = 0.85f),
                            offset = DpOffset((-4).dp, (-4).dp),
                            radius = 10.dp
                        )
                    )
                    .background(Color(0xFFE0E5EC), RoundedCornerShape(50.dp))
                    .clip(RoundedCornerShape(50.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onToggle
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                // The Thumb
                Box(
                    modifier = Modifier
                        .offset(x = thumbOffset + 5.dp)
                        .size(110.dp, 90.dp)
                        .neumorphicRaised(
                            shape = RoundedCornerShape(45.dp),
                            elevation = 8.dp,
                            darkColor = Color.Black.copy(alpha = 0.1f)
                        )
                        .background(Color(0xFFE0E5EC), RoundedCornerShape(45.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1.15f))
                        
                        // Divider
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight(0.6f)
                                .background(Color.Black.copy(alpha = 0.06f))
                        )
                        
                        Box(
                            modifier = Modifier.weight(0.85f),
                            contentAlignment = Alignment.Center
                        ) {
                            ArrowIcon(
                                direction = ArrowDirection.Right,
                                size = 20.dp,
                                color = Color.Black.copy(alpha = 0.8f),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class ArrowDirection { Left, Right }

@Composable
fun ArrowIcon(
    direction: ArrowDirection,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = Color.Black.copy(alpha = 0.8f),
    strokeWidth: Dp = 3.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val sw = strokeWidth.toPx()

        val path = Path().apply {
            if (direction == ArrowDirection.Left) {
                moveTo(w * 0.75f, h / 2f)
                lineTo(w * 0.25f, h / 2f)
                moveTo(w * 0.45f, h * 0.35f)
                lineTo(w * 0.25f, h / 2f)
                lineTo(w * 0.45f, h * 0.65f)
            } else {
                moveTo(w * 0.25f, h / 2f)
                lineTo(w * 0.75f, h / 2f)
                moveTo(w * 0.55f, h * 0.35f)
                lineTo(w * 0.75f, h / 2f)
                lineTo(w * 0.55f, h * 0.65f)
            }
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = sw,
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )
    }
}

fun Modifier.neumorphicRaised(
    shape: RoundedCornerShape,
    elevation: Dp = 10.dp,
    lightColor: Color = Color.White,
    darkColor: Color = Color(0xFFA3B1C6).copy(alpha = 0.6f)
): Modifier = this.drawWithContent {
    val shadowRadius = elevation.toPx()
    val shadowOffset = (elevation / 2).toPx()
    val cornerRadius = shape.topStart.toPx(size, this)

    drawIntoCanvas { canvas ->
        val paint = Paint().asFrameworkPaint()
        paint.isAntiAlias = true
        
        // Dark shadow
        val darkShadowColor = darkColor.toArgb()
        paint.color = Color.Transparent.toArgb()
        paint.setShadowLayer(shadowRadius, shadowOffset, shadowOffset, darkShadowColor)
        canvas.nativeCanvas.drawRoundRect(
            0f, 0f, size.width, size.height,
            cornerRadius, cornerRadius,
            paint
        )

        // Light shadow
        val lightShadowColor = lightColor.toArgb()
        paint.color = Color.Transparent.toArgb()
        paint.setShadowLayer(shadowRadius, -shadowOffset, -shadowOffset, lightShadowColor)
        canvas.nativeCanvas.drawRoundRect(
            0f, 0f, size.width, size.height,
            cornerRadius, cornerRadius,
            paint
        )
    }
    drawContent()
}

@Preview(showBackground = true, backgroundColor = 0xFFDDE2E9)
@Composable
private fun LightSwitchPreview() {
    NeumorphicTheme {
        LightSwitchDemo()
    }
}
