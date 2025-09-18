package com.naulian.neumorphic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@Composable
fun Modifier.neumorphicUp(
    shape: Shape,
    shadowPadding: Dp,
    color: Color = NeumorphicTheme.colorScheme.background,
): Modifier {
    return background(
        color = color,
        shape = shape
    )
        .innerShadow(
            shape = shape,
            shadow = Shadow(
                radius = shadowPadding,
                color = if (color == NeumorphicTheme.colorScheme.background) {
                    NeumorphicTheme.colorScheme.light
                } else NeumorphicTheme.colorScheme.onColorLight,
                offset = DpOffset(x = shadowPadding, y = shadowPadding)
            )
        )
        .innerShadow(
            shape = shape,
            shadow = Shadow(
                radius = shadowPadding,
                color = if (color == NeumorphicTheme.colorScheme.background) {
                    NeumorphicTheme.colorScheme.shadow
                } else NeumorphicTheme.colorScheme.onColorShadow,
                offset = DpOffset(x = -shadowPadding, y = -shadowPadding)
            )
        )
}

@Composable
fun Modifier.neumorphicDown(
    shape: Shape,
    shadowPadding: Dp,
    color: Color = NeumorphicTheme.colorScheme.background,
) = background(
    color = color,
    shape = shape
)
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = if (color == NeumorphicTheme.colorScheme.background) {
                NeumorphicTheme.colorScheme.light
            } else NeumorphicTheme.colorScheme.onColorLight,
            offset = DpOffset(x = -shadowPadding, y = -shadowPadding)
        )
    )
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = if (color == NeumorphicTheme.colorScheme.background) {
                NeumorphicTheme.colorScheme.shadow
            } else NeumorphicTheme.colorScheme.onColorShadow,
            offset = DpOffset(x = shadowPadding, y = shadowPadding)
        )
    )


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