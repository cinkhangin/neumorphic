package com.example.neumorphic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ckgin.modify.HugeIcons
import com.ckgin.neumorphic.NeumorphicPreviewColumn
import com.ckgin.neumorphic.NeumorphicTheme
import com.ckgin.neumorphic.new.NeuIconButton
import com.ckgin.neumorphic.demo.SquarcleShape


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            NeumorphicTheme {
                NeumorphicPreviewColumn {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        NeuIconButton(
                            onClick = {},
                            shape = CircleShape
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(HugeIcons.Home),
                                contentDescription = "Account"
                            )
                        }

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
                            shape = CircleShape
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
                                painter = painterResource(HugeIcons.Home),
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
                                painter = painterResource(HugeIcons.Location),
                                contentDescription = "Account"
                            )
                        }

                        NeuIconButton(
                            onClick = {},
                            shape = CircleShape,
                            containerColor = Color.hsl(
                                hue = 300f,
                                saturation = 0.2f,
                                lightness = 0.5f
                            ),
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(HugeIcons.Favourite),
                                contentDescription = "Account"
                            )
                        }
                    }
                }
            }
        }
    }
}