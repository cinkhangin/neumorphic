package com.ckgin.neumorphic.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ckgin.modify.themeColors
import com.ckgin.neumorphic.NeumorphicTheme

@Composable
fun NeuContainer(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape
) {

    Box(
        modifier = modifier
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 20.dp,
                    color = NeumorphicTheme.colorScheme.darkShadow,
                    offset = DpOffset(x = (-20).dp, y = (20).dp)
                )
            )
            .background(
                color = themeColors.background,
                shape = shape
            )
            .clip(shape)
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 40.dp,
                    color = NeumorphicTheme.colorScheme.light.copy(
                        0.5f
                    ),
                    offset = DpOffset(x = (40).dp, y = (-40).dp)
                )
            )
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 40.dp,
                    color = NeumorphicTheme.colorScheme.shadow.copy(
                        0.2f
                    ),
                    offset = DpOffset(x = (-40).dp, y = (40).dp)
                )
            )
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 1.dp,
                    color = NeumorphicTheme.colorScheme.light,
                    offset = DpOffset(x = (-1).dp, y = (1).dp)
                )
            )
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 1.dp,
                    color = NeumorphicTheme.colorScheme.shadow,
                    offset = DpOffset(x = (1).dp, y = (-1).dp)
                )
            )
            .padding(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = themeColors.background,
                    shape = shape
                )
                .clip(shape)
                .innerShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 1.dp,
                        color = NeumorphicTheme.colorScheme.light,
                        offset = DpOffset(x = (1).dp, y = (-1).dp)
                    )
                )
                .innerShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 1.dp,
                        color = NeumorphicTheme.colorScheme.shadow,
                        offset = DpOffset(x = (-1).dp, y = (1).dp)
                    )
                )
                .padding(1.dp)
                .innerShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 20.dp,
                        color = NeumorphicTheme.colorScheme.shadow.copy(
                            0.1f
                        ),
                        offset = DpOffset(x = (20).dp, y = (-20).dp)
                    )
                )
                .innerShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 70.dp,
                        color = NeumorphicTheme.colorScheme.shadow.copy(
                            0.5f
                        ),
                        offset = DpOffset(x = (-70).dp, y = (70).dp)
                    )
                )
                .innerShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 20.dp,
                        color = NeumorphicTheme.colorScheme.darkShadow.copy(
                            0.5f
                        ),
                        offset = DpOffset(x = (-20).dp, y = (20).dp)
                    )
                )
        )
    }
}

@Preview
@Composable
private fun NeuContainerPreview() {
    NeumorphicTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(themeColors.background)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            NeuContainer(
                modifier = Modifier.size(200.dp)
            )
        }
    }
}