package com.naulian.neumorphic

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun Down(
    modifier: Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.background,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape: Shape = ButtonDefaults.shape,
    content: @Composable RowScope.() -> Unit
) {

    var touch by remember { mutableStateOf(false) }
    val shadowPadding by animateDpAsState(
        targetValue = if (touch) 4.dp else 6.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Row(
        modifier = modifier
            .neumorphicDown(
                color = color,
                shape = shape,
                shadowPadding = shadowPadding,
            )
            .defaultMinSize(
                minWidth = 58.dp,
                minHeight = 40.dp
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
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onBackground
        ) {
            content()
        }
    }
}

@Composable
internal fun Up(
    modifier: Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.background,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape: Shape = ButtonDefaults.shape,
    content: @Composable RowScope.() -> Unit
) {

    var touch by remember { mutableStateOf(false) }
    val shadowPadding by animateDpAsState(
        targetValue = if (touch) 2.dp else 4.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Row(
        modifier = modifier
            .neumorphicUp(
                color = color,
                shape = shape,
                shadowPadding = shadowPadding,
            )
            .defaultMinSize(
                minWidth = 58.dp,
                minHeight = 40.dp
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
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onBackground
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun LightPreview() {
    ComposableTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Up {
                Text(text = "Neumorphic Up")
            }

            Down {
                Text(text = "Neumorphic Down")
            }

            Up(
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(text = "Neumorphic Up")
            }

            Down(
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(text = "Neumorphic Down")
            }
        }
    }
}

@Preview
@Composable
private fun DarkPreview() {
    ComposableTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Up {
                Text(text = "Neumorphic Up")
            }

            Down {
                Text(text = "Neumorphic Down")
            }

            Up(
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(text = "Neumorphic Up")
            }

            Down(
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(text = "Neumorphic Down")
            }
        }
    }
}