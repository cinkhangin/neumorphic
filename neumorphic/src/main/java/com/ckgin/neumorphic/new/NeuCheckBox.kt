package com.ckgin.neumorphic.new

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ckgin.modify.HugeIcons
import com.ckgin.neumorphic.NeumorphicPreviewSquare
import com.ckgin.neumorphic.NeumorphicTheme

@Composable
fun NeumorphicCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(20),
    thumbPadding: Dp = 4.dp
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

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
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 20.dp,
                    color = NeumorphicTheme.colorScheme.light.copy(
                        0.5f
                    ),
                    offset = DpOffset(x = (20).dp, y = (-20).dp)
                )
            )
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 20.dp,
                    color = NeumorphicTheme.colorScheme.shadow.copy(
                        0.2f
                    ),
                    offset = DpOffset(x = (-20).dp, y = (20).dp)
                )
            )
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
            .padding(thumbPadding)
    ) {
        if (checked) {
            Box(
                modifier = Modifier
                    .size(32.dp - (thumbPadding * 2))
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(HugeIcons.Done),
                    contentDescription = "Check Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun NeumorphicCheckBoxPreview() {
    NeumorphicPreviewSquare {

        var checked by remember { mutableStateOf(false) }

        NeumorphicCheckBox(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        NeumorphicCheckBox(
            checked = !checked,
            onCheckedChange = { checked = !it }
        )
    }
}