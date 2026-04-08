package com.ckgin.neumorphic.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicPreviewBox
import com.ckgin.neumorphic.NeumorphicTheme

@Composable
fun NeuCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 10.dp,
                    color = NeumorphicTheme.colorScheme.black.copy(0.8f),
                    offset = DpOffset(x = (-10).dp, y = (10).dp)
                )
            )
            .background(
                color = NeumorphicTheme.colorScheme.background,
                shape = CircleShape
            )
            .innerShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 5.dp,
                    color = NeumorphicTheme.colorScheme.light,
                    offset = DpOffset(x = (-5).dp, y = (5).dp)
                )
            )
            .innerShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 5.dp,
                    color = NeumorphicTheme.colorScheme.darkShadow,
                    offset = DpOffset(x = (5).dp, y = (-5).dp)
                )
            )
            .padding(5.dp)
            .background(
                color = NeumorphicTheme.colorScheme.background,
                shape = CircleShape
            )
            .innerShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 1.dp,
                    color = NeumorphicTheme.colorScheme.light,
                    offset = DpOffset(x = (-1).dp, y = (1).dp)
                )
            )
            .innerShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 1.dp,
                    color = NeumorphicTheme.colorScheme.shadow,
                    offset = DpOffset(x = (1).dp, y = (-1).dp)
                )
            )
    )
}

@Preview
@Composable
private fun NeuCirclePreview() {
    NeumorphicPreviewBox {
        NeuCircle(modifier = Modifier.size(100.dp))
    }
}