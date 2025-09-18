package com.naulian.neumorphic

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.placeCursorAtEnd
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naulian.modify.HugeIcons
import com.naulian.modify.field.setText
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeumorphismComponentShowcase() {

    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            checked = !checked
        }
    }

    val textState = rememberTextFieldState()

    LaunchedEffect(Unit) {
        while (true) {
            "hello@example.com".forEach {
                delay(200)
                textState.edit {
                    append(it)
                    placeCursorAtEnd()
                }
            }

            delay(1000) // pause before clearing
            textState.setText("")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
    ) {
        NeumorphicBorder(
            modifier = Modifier.fillMaxWidth(),
            cornerRadiusDp = 10.dp,
            borderThickness = 2.dp,
            contentPadding = PaddingValues(20.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                NeumorphicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    state = textState
                )

                NeumorphicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    state = TextFieldState(),
                    placeHolder = "Text Field with Placeholder"
                )

                NeumorphicButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    enabled = checked
                ) {
                    Text(text = "Button")
                }

                NeumorphicButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    enabled = !checked
                ) {
                    Text(text = "Disabled Button")
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            NeumorphicIconButton(onClick = {}) {
                Icon(
                    painter = painterResource(HugeIcons.Favourite),
                    contentDescription = "Fav"
                )
            }

            NeumorphicIconButton(onClick = {}) {
                Icon(
                    painter = painterResource(HugeIcons.Delete),
                    contentDescription = "Fav"
                )
            }

            NeumorphicIconButton(onClick = {}) {
                Icon(
                    painter = painterResource(HugeIcons.Copy),
                    contentDescription = "Fav"
                )
            }

            NeumorphicIconButton(onClick = {}) {
                Icon(
                    painter = painterResource(HugeIcons.Home),
                    contentDescription = "Fav"
                )
            }
        }

        NeumorphicUpHorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Switch", color = MaterialTheme.colorScheme.onBackground)

            NeumorphicSwitch(checked = checked, onCheckedChange = { checked = it })
        }

        val infiniteTransition = rememberInfiniteTransition()
        val progress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = InfiniteRepeatableSpec(
                animation = tween(
                    durationMillis = 4000,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )

        Text("Linear Progress Indicator at ${(progress * 100).roundToInt()}%", color = MaterialTheme.colorScheme.onBackground)
        NeumorphicLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { progress }
        )

        val sliderState = remember {
            SliderState(
                valueRange = 0f..1f,
                onValueChangeFinished = {},
                steps = 10
            )
        }

        Text("Slider ${(sliderState.value * 100).roundToInt()}", color = MaterialTheme.colorScheme.onBackground)
        NeumorphicSlider(
            modifier = Modifier.fillMaxWidth(),
            state = sliderState
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Check Boxes", color = MaterialTheme.colorScheme.onBackground)

            NeumorphicCheckBox(checked = checked, onCheckedChange = { checked = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Radio Button", color = MaterialTheme.colorScheme.onBackground)

            NeumorphicRadioButton(checked = checked, onCheckedChange = { checked = it })
        }

    }
}

@Preview
@Composable
private fun ShowcaseLightPreview() {
    NeumorphicPreviewScreen {
        NeumorphismComponentShowcase()
    }
}

@Preview
@Composable
private fun ShowcaseDarkPreview() {
    NeumorphicPreviewScreen(dark = true) {
        NeumorphismComponentShowcase()
    }
}