package com.naulian.neumorphic

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicRadioButton(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    thumbPadding: Dp = 4.dp
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val shadowPadding by animateDpAsState(
        targetValue = when {
            enabled -> if (checked) 4.dp else 0.dp
            else -> 1.dp
        },
        animationSpec = tween(
            durationMillis = 400
        )
    )

    val toggleableModifier =
        if (onCheckedChange != null) {
            Modifier
                .minimumInteractiveComponentSize()
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChange,
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = interactionSource,
                    indication = null
                )
        } else {
            Modifier
        }

    Box(
        modifier = modifier
            .then(toggleableModifier)
            .size(32.dp)
            .neumorphicDown(
                shape = CircleShape,
                shadowPadding = 4.dp,
            )
            .padding(thumbPadding)
    ) {
        if (checked) {
            Box(
                modifier = Modifier
                    .size(32.dp - (thumbPadding * 2))
                    .neumorphicUp(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        shadowPadding = shadowPadding
                    ),
                contentAlignment = Alignment.Center
            ) {}
        }
    }
}

@Preview
@Composable
private fun NeumorphicRadioButtonPreview() {
    NeumorphicPreviewSquare {

        var checked by remember { mutableStateOf(false) }

        NeumorphicRadioButton(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        NeumorphicRadioButton(
            checked = !checked,
            onCheckedChange = { checked = !it }
        )
    }
}