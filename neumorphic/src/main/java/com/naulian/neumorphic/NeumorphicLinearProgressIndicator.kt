package com.naulian.neumorphic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicLinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    trackThickness: Dp = 10.dp,
    cornerRadiusDp: Dp = 4.dp,
    indicatorPadding: Dp = 0.dp
) {
    val coercedProgress = { progress().coerceIn(0f, 1f) }

    var boxWidth by remember { mutableIntStateOf(0) }

    val indicatorHeight = trackThickness - (indicatorPadding * 2)
    val indicatorWidth = with(LocalDensity.current){
        boxWidth.toDp() - (indicatorPadding * 2)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = coercedProgress(),
    )

    Box(
        modifier = modifier
            .onGloballyPositioned {
                 boxWidth = it.size.width
            }
            .height(trackThickness)
            .neumorphicDown(
                shape = RoundedCornerShape(cornerRadiusDp),
                shadowPadding = 2.dp
            )
            .padding(indicatorPadding)
    ) {
        Box(
            modifier = Modifier
                .width(indicatorWidth * animatedProgress)
                .height(indicatorHeight)
                .neumorphicUp(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(cornerRadiusDp - indicatorPadding),
                    shadowPadding = 2.dp
                )
        )
    }
}

@Preview
@Composable
private fun NeumorphicLinearProgressIndicatorPreview() {
    NeumorphicPreviewColumn {
        NeumorphicLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { 0f }
        )

        NeumorphicLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { 0.5f }
        )
        NeumorphicLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { 1f }
        )
    }
}