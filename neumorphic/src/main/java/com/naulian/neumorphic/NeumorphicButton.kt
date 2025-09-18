package com.naulian.neumorphic

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun NeumorphicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeumorphicButtonDefaults.shape,
    colors: ButtonColors = NeumorphicButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = NeumorphicButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {

    var touch by remember { mutableStateOf(false) }
    val shadowPadding by animateDpAsState(
        targetValue = when {
            enabled -> if (touch) 2.dp else 4.dp
            else -> 1.dp
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor

    val mergedStyle = LocalTextStyle.current.merge(MaterialTheme.typography.labelLarge)
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle
    ) {
        Row(
            modifier = modifier
                .semantics { role = Role.Button }
                .neumorphicUp(
                    shape = shape,
                    color = containerColor,
                    shadowPadding = shadowPadding
                )
                .defaultMinSize(
                    minWidth = NeumorphicButtonDefaults.MinWidth,
                    minHeight = NeumorphicButtonDefaults.MinHeight
                )
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()
                            touch = change.pressed
                        }
                    }
                }
                .clip(shape)
                .clickable(enabled) { onClick() }
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Preview
@Composable
private fun NeumorphicButtonPreview() {
    NeumorphicPreviewSquare {
        NeumorphicButton(onClick = {}) {
            Text(text = "Neumorphic Button")
        }

        NeumorphicButton(enabled = false, onClick = {}, shape = RoundedCornerShape(20)) {
            Text(text = "Disabled Neumorphic Button")
        }
    }
}