package com.ckgin.neumorphic.new

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ckgin.modify.HugeIcons
import com.ckgin.neumorphic.NeumorphicPreviewColumn
import com.ckgin.neumorphic.NeumorphicTheme
import com.ckgin.neumorphic.demo.SquarcleShape
import com.ckgin.neumorphic.demo.light
import com.ckgin.neumorphic.demo.neuBackground
import com.ckgin.neumorphic.demo.neuEdgeDown
import com.ckgin.neumorphic.demo.neuEdgeUp
import com.ckgin.neumorphic.demo.shadow

@Composable
fun NeuIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    enabled: Boolean = true,
    containerSize: Dp = 40.dp,
    containerColor: Color = NeumorphicTheme.colorScheme.background,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    val contentColor = if (containerColor.luminance() > 0.5f) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onPrimary

    val actualInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val isPressed by actualInteractionSource.collectIsPressedAsState()

    val animatedLightColor by animateColorAsState(
        targetValue = when {
            enabled -> if (isPressed) containerColor.shadow(0.2f) else containerColor.light()
            else -> containerColor.light()
        },
        animationSpec = tween(
            durationMillis = 400
        ),
        label = "lightColor"
    )

    val animatedShadowColor by animateColorAsState(
        targetValue = when {
            enabled -> if (isPressed) containerColor.shadow(0.2f) else containerColor.shadow()
            else -> containerColor.shadow()
        },
        animationSpec = tween(
            durationMillis = 400
        ),
        label = "shadowColor"
    )

    val animatedContainerColor by animateColorAsState(
        targetValue = when {
            enabled -> if (isPressed) containerColor.shadow(0.1f) else containerColor
            else -> containerColor
        },
        animationSpec = tween(
            durationMillis = 400
        ),
        label = "containerColor"
    )

    Box(
        modifier = modifier
            .size(containerSize)
            .aspectRatio(1f)
            .neuBackground(shape = shape)
            .neuEdgeDown(shape, radius = 3.dp, offset = 3.dp)
            .padding(2.dp)
            .background(color = Color.DarkGray, shape = shape)
            .padding(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = animatedContainerColor, shape = shape)
                .neuEdgeUp(
                    shape,
                    radius = 1.dp,
                    offset = 3.dp,
                    shadow = animatedShadowColor,
                    light = animatedLightColor
                )
                .padding(2.dp)
                .background(color = animatedContainerColor, shape = shape)
                .clip(shape)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = actualInteractionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
        }
    }
}

@Preview
@Composable
private fun NeuIconButtonPreview() {
    NeumorphicPreviewColumn {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NeuIconButton(
                onClick = {},
                shape = SquarcleShape(n = 3f)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Location),
                    contentDescription = "Account"
                )
            }
            
            NeuIconButton(
                onClick = {},
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Home),
                    contentDescription = "Account"
                )
            }

            NeuIconButton(
                onClick = {},
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Favourite),
                    contentDescription = "Account"
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NeuIconButton(
                onClick = {},
                shape = CircleShape,
                containerColor = Color.hsl(
                    hue = 120f,
                    saturation = 0.2f,
                    lightness = 0.5f
                ),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Notification),
                    contentDescription = "Account"
                )
            }

            NeuIconButton(
                onClick = {},
                shape = SquarcleShape(n = 3f),
                containerColor = Color.hsl(
                    hue = 240f,
                    saturation = 0.2f,
                    lightness = 0.5f
                ),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Gift),
                    contentDescription = "Account"
                )
            }

            NeuIconButton(
                onClick = {},
                shape = RoundedCornerShape(20),
                containerColor = Color.hsl(
                    hue = 300f,
                    saturation = 0.2f,
                    lightness = 0.5f
                ),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(HugeIcons.Bookmark2),
                    contentDescription = "Account"
                )
            }
        }
    }
}