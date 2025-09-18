package com.naulian.neumorphic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicUpHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .neumorphicUp(
                shape = shape,
                shadowPadding = if (thickness >= 4.dp) thickness / 4 else 1.dp
            )
    )
}

@Composable
fun NeumorphicUpVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
            .neumorphicUp(
                shape = shape,
                shadowPadding = if (thickness >= 4.dp) thickness / 4 else 1.dp
            )
    )
}

@Composable
fun NeumorphicDownHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .neumorphicDown(
                shape = shape,
                shadowPadding = if (thickness >= 4.dp) thickness / 4 else 1.dp
            )
    )
}

@Composable
fun NeumorphicDownVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
            .neumorphicDown(
                shape = shape,
                shadowPadding = if (thickness >= 4.dp) thickness / 4 else 1.dp
            )
    )
}

@Preview
@Composable
private fun NeumorphicDividerPreview() {
    NeumorphicPreviewSquare {
        NeumorphicUpHorizontalDivider(thickness = 8.dp)
        NeumorphicDownHorizontalDivider(thickness = 8.dp)
        Row {
            NeumorphicUpVerticalDivider(thickness = 8.dp)
            Spacer(modifier = Modifier.width(10.dp))
            NeumorphicDownVerticalDivider(thickness = 8.dp)
        }
    }
}