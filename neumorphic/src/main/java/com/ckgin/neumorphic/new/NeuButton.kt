package com.ckgin.neumorphic.new

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicButtonDefaults
import com.ckgin.neumorphic.NeumorphicPreviewBox
import com.ckgin.neumorphic.demo.light
import com.ckgin.neumorphic.demo.neuBackground
import com.ckgin.neumorphic.demo.neuEdgeDown
import com.ckgin.neumorphic.demo.neuEdgeUp
import com.ckgin.neumorphic.demo.shadow


@Composable
fun NeuButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeumorphicButtonDefaults.shape,
    colors: ButtonColors = NeumorphicButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = NeumorphicButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {

    var touch by remember { mutableStateOf(false) }
    val color = if (enabled) colors.containerColor else colors.disabledContainerColor

    val lightColor by animateColorAsState(
        targetValue = when {
            enabled -> if (touch) color.shadow(0.1f) else color.light()
            else -> color.light()
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    val shadowColor by animateColorAsState(
        targetValue = when {
            enabled -> if (touch) color.shadow(0.1f) else color.shadow()
            else -> color.shadow()
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    val containerColor by animateColorAsState(
        targetValue = when {
            enabled -> if (touch) color.shadow(0.1f) else color
            else -> color
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val mergedStyle = LocalTextStyle.current.merge(MaterialTheme.typography.labelLarge)

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle
    ) {
        Box(
            modifier = modifier
                .semantics { role = Role.Button }
                .defaultMinSize(
                    minWidth = NeumorphicButtonDefaults.MinWidth,
                    minHeight = NeumorphicButtonDefaults.MinHeight
                )
                .neuBackground(shape = shape)
                .neuEdgeDown(shape, radius = 3.dp, offset = 3.dp)
                .padding(3.dp)
                .background(color = Color.DarkGray, shape = shape)
                .padding(2.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = containerColor, shape = shape)
                    .neuEdgeUp(
                        shape,
                        radius = 1.dp,
                        offset = 3.dp,
                        shadow = shadowColor,
                        light = lightColor
                    )
                    .padding(2.dp)
                    .background(color = containerColor, shape = shape)
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
}


@Preview
@Composable
private fun NeuButtonPreview() {
    NeumorphicPreviewBox {
        NeuButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = CircleShape,
            onClick = {}
        ) {
            Text(text = "Button")
        }
    }
}

