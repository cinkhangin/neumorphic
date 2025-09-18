package com.naulian.neumorphic

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.modify.HugeIcons

@Composable
fun NeumorphicIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerSize: Dp = 40.dp,
    shape: Shape = CircleShape,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    val contentColor = if (enabled) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    var touch by remember { mutableStateOf(false) }
    val shadowPadding by animateDpAsState(
        targetValue = when {
            enabled -> if (touch) 2.dp else 3.dp
            else -> 2.dp
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    Box(
        modifier =
            modifier
                .minimumInteractiveComponentSize()
                .size(containerSize)
                .neumorphicUp(
                    shape = shape,
                    shadowPadding = shadowPadding,
                )
                .clip(shape)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()
                            touch = change.pressed
                        }
                    }
                }
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}


@Preview
@Composable
private fun NeumorphicIconButtonPreview() {
    NeumorphicPreviewSquare {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NeumorphicIconButton(onClick = {}) {
                Icon(painter = painterResource(HugeIcons.Account), contentDescription = "Account")
            }

            NeumorphicIconButton(onClick = {}, shape = RoundedCornerShape(20)) {
                Icon(painter = painterResource(HugeIcons.Search), contentDescription = "Search")
            }
        }
    }
}