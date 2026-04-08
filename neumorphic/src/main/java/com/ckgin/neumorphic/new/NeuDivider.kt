package com.ckgin.neumorphic.new

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
import com.ckgin.neumorphic.NeumorphicPreviewSquare
import com.ckgin.neumorphic.demo.neuEdgeDown
import com.ckgin.neumorphic.demo.neuEdgeUp

@Composable
fun NeuUpHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .neuEdgeUp(shape)
    )
}

@Composable
fun NeuUpVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
            .neuEdgeUp(shape)
    )
}

@Composable
fun NeuDownHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .neuEdgeDown(shape)
    )
}

@Composable
fun NeuDownVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    shape: Shape = RectangleShape
) {

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
            .neuEdgeDown(shape)
    )
}

@Preview
@Composable
private fun NeumorphicDividerPreview() {
    NeumorphicPreviewSquare {
        NeuUpHorizontalDivider(thickness = 8.dp)
        NeuDownHorizontalDivider(thickness = 8.dp)
        Row {
            NeuUpVerticalDivider(thickness = 8.dp)
            Spacer(modifier = Modifier.width(10.dp))
            NeuDownVerticalDivider(thickness = 8.dp)
        }
    }
}