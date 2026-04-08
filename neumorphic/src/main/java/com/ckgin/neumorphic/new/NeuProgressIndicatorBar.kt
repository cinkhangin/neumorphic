package com.ckgin.neumorphic.new

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicPreviewBox
import com.ckgin.neumorphic.NeumorphicTheme
import com.ckgin.neumorphic.demo.neuBackground
import com.ckgin.neumorphic.demo.neuDownShadow
import com.ckgin.neumorphic.demo.neuEdgeDown
import com.ckgin.neumorphic.demo.neuEdgeUp

@Composable
fun NeuProgressIndicatorBar(
    modifier: Modifier = Modifier,
    shape: Shape
) {
    Box(
        modifier = modifier
            .neuBackground(shape = shape)
            .neuEdgeDown(shape)
            .padding(1.dp)
            .neuDownShadow(
                shape = shape,
                radius = 7.dp,
                offset = 3.dp,
                shadow = NeumorphicTheme.colorScheme.shadow
            )
            .neuDownShadow(
                shape = shape,
                shadow = NeumorphicTheme.colorScheme.darkShadow
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(color = Color(0xFF00C853), shape = shape)
                .neuEdgeUp(
                    shape = shape,
                    light = Color(0xFF00FF6C),
                    shadow = Color(0xFF00461B)
                )
                .padding(1.dp)
                .background(color = Color(0xFF00C853), shape = shape)
        )
    }
}

@Preview
@Composable
private fun NeuProgressIndicatorBarPreview() {
    NeumorphicPreviewBox {
        NeuProgressIndicatorBar(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(12.dp),
            shape = CircleShape
        )
    }
}