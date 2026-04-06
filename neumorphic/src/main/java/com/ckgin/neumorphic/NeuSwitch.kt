package com.ckgin.neumorphic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ckgin.modify.themeColors

@Composable
fun NeuSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val offset by animateDpAsState(
        targetValue = if (checked) 120.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val color by animateColorAsState(
        targetValue = if (checked) themeColors.primary else themeColors.background,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(themeColors.background)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(
                    color = themeColors.background,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .toggleable(checked, enabled) {
                    onCheckedChange?.invoke(!checked)
                }
                .innerShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 40.dp,
                        color = NeumorphicTheme.colorScheme.light.copy(0.5f),
                        offset = DpOffset(x = (40).dp, y = (-40).dp)
                    )
                )
                .innerShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 40.dp,
                        color = NeumorphicTheme.colorScheme.shadow.copy(0.2f),
                        offset = DpOffset(x = (-40).dp, y = (40).dp)
                    )
                )
                .innerShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 2.dp,
                        color = NeumorphicTheme.colorScheme.light,
                        offset = DpOffset(x = (2).dp, y = (-2).dp)
                    )
                )
                .innerShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 2.dp,
                        color = NeumorphicTheme.colorScheme.shadow,
                        offset = DpOffset(x = (-2).dp, y = (2).dp)
                    )
                )
                .padding(20.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(10.dp)
                    .aspectRatio(2f / 1f)
                    .drawBehind {
                        val radiusPx = size.minDimension * (50 / 100f)

                        drawRoundRect(
                            color = color,
                            cornerRadius = CornerRadius(radiusPx, radiusPx)
                        )
                    }
                    .innerShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 2.dp,
                            color = NeumorphicTheme.colorScheme.light.copy(0.9f),
                            offset = DpOffset(x = (2).dp, y = (-2).dp)
                        )
                    )
                    .innerShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 2.dp,
                            color = NeumorphicTheme.colorScheme.shadow,
                            offset = DpOffset(x = (-2).dp, y = (2).dp)
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .size(180.dp)
                    .aspectRatio(1f)
                    .padding(5.dp)
                    .offset {
                        IntOffset(x = offset.toPx().toInt(), y = 0)
                    }
                    .dropShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 10.dp,
                            color = NeumorphicTheme.colorScheme.darkShadow,
                            offset = DpOffset(x = (-10).dp, y = (10).dp)
                        )
                    )
                    .background(
                        color = themeColors.background,
                        shape = CircleShape
                    )
                    .innerShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 2.dp,
                            color = NeumorphicTheme.colorScheme.light,
                            offset = DpOffset(x = (-2).dp, y = (2).dp)
                        )
                    )
                    .innerShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 2.dp,
                            color = NeumorphicTheme.colorScheme.darkShadow,
                            offset = DpOffset(x = (2).dp, y = (-2).dp)
                        )
                    )
            )
        }
    }
}

@Preview
@Composable
private fun NeuSwitchPreview() {
    NeumorphicTheme {

        var checked by remember {
            mutableStateOf(false)
        }

        NeuSwitch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}