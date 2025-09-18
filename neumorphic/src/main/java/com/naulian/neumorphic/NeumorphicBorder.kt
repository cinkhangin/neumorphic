package com.naulian.neumorphic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicBorder(
    modifier: Modifier = Modifier,
    cornerRadiusDp: Dp = 40.dp,
    borderThickness: Dp = 10.dp,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .neumorphicUp(
                shape = RoundedCornerShape(cornerRadiusDp),
                shadowPadding = 2.dp
            )
            .padding(borderThickness)
    ) {
        Box(
            modifier = modifier
                .neumorphicDown(
                    shape = RoundedCornerShape(cornerRadiusDp - borderThickness),
                    shadowPadding = 2.dp
                )
                .padding(contentPadding),
            content = content
        )
    }
}

@Preview
@Composable
private fun NeumorphicBorderPreview() {
    NeumorphicPreviewSquare {
        NeumorphicBorder(
            modifier = Modifier.size(300.dp)
        ) {

        }
    }
}