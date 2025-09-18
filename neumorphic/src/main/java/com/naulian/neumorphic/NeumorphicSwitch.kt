package com.naulian.neumorphic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun NeumorphicSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    neumorphicDepth: Dp = 4.dp,
    shape: Shape = CircleShape
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val toggleableModifier =
        if (onCheckedChange != null) {
            Modifier
                .minimumInteractiveComponentSize()
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChange,
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = interactionSource,
                    indication = null
                )
        } else Modifier

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 32.dp else 0.dp,
        label = "thumbOffset",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val thumbPadding by animateDpAsState(
        targetValue = if(checked) 2.dp else 4.dp,
        label = "thumbPadding",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val accent by animateColorAsState(
        targetValue = if(checked) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.background,
        label = "accent",
        animationSpec = spring()
    )

    Box(
        modifier = modifier
            .then(toggleableModifier)
            .width(64.dp)
            .height(32.dp)
    ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(32.dp)
                .neumorphicDown(
                    shape = shape,
                    color = accent,
                    shadowPadding = neumorphicDepth,
                )
        )

        Box(
            modifier = Modifier
                .padding(thumbPadding)
                .size(32.dp - (thumbPadding * 2))
                .offset {
                    IntOffset(x = thumbOffset.toPx().toInt(), y = 0)
                }
                .dropShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = neumorphicDepth,
                        color = MaterialTheme.colorScheme.surfaceDim,
                        offset = DpOffset(x = neumorphicDepth, y = neumorphicDepth)
                    )
                )
                .neumorphicUp(
                    shape = shape,
                    shadowPadding = 4.dp
                )
        )
    }
}

@Preview
@Composable
private fun NeumorphicSwitchPreview() {
    NeumorphicPreviewColumn(
        modifier = Modifier.size(200.dp)
    ) {

        var checked by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(2000)
                checked = !checked
            }
        }
        
        NeumorphicSwitch(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        NeumorphicSwitch(
            checked = !checked,
            onCheckedChange = { checked = !it }
        )
    }
}