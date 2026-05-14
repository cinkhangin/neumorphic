package com.ckgin.neumorphic.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ckgin.modify.HugeIcons
import com.ckgin.neumorphic.NeumorphicPreviewColumn
import com.ckgin.neumorphic.new.NeuButton
import com.ckgin.neumorphic.new.NeuIconButton

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

        NeuButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("RoundedCorner Button")
        }

        NeuButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(56.dp),
            shape = CircleShape
        ) {
            Text("CircleShape button")
        }
    }
}