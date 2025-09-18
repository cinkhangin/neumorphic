package com.naulian.neumorphic

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.modify.touchIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeumorphicSlider(
    state: SliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trackThickness: Dp = 16.dp,
    trackShape: Shape = CircleShape,
    thumbSize: Dp = 24.dp,
    thumbShape: Shape = CircleShape,
    indicatorPadding: Dp = 4.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Slider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        thumb = {
            Box(
                modifier = Modifier
                    .size(thumbSize)
                    .touchIndicator {}
                    .neumorphicUp(
                        shape = thumbShape,
                        color = MaterialTheme.colorScheme.primary,
                        shadowPadding = 2.dp
                    )
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = state,
                        onDragStopped = { state.onValueChangeFinished?.invoke() }
                    )
            )
        },
        track = {
            var boxWidth by remember { mutableIntStateOf(0) }
            val indicatorWidth = with(LocalDensity.current) {
                boxWidth.toDp() - (indicatorPadding * 2)
            }

            Box(
                modifier = modifier
                    .onGloballyPositioned {
                        boxWidth = it.size.width
                    }
                    .height(trackThickness)
                    .neumorphicDown(
                        shape = trackShape,
                        shadowPadding = 2.dp
                    )
            )

            Box(
                modifier = Modifier
                    .padding(indicatorPadding)
                    .height(trackThickness - (indicatorPadding * 2))
                    .width(indicatorWidth * (state.value / state.valueRange.endInclusive))
                    .neumorphicUp(
                        shape = trackShape,
                        color = MaterialTheme.colorScheme.primary,
                        shadowPadding = 1.dp
                    )
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NeumorphicSliderPreview() {
    NeumorphicPreviewSquare {

        val sliderState = remember {
            SliderState(
                valueRange = 0f..100f,
                onValueChangeFinished = {},
                steps = 20
            )
        }

        NeumorphicSlider(
            modifier = Modifier.fillMaxWidth(),
            state = sliderState
        )
    }
}