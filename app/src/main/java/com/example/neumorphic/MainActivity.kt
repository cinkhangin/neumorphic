package com.example.neumorphic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ckgin.neumorphic.NeuSwitch
import com.ckgin.neumorphic.NeumorphicTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            NeumorphicTheme {
                var checked by remember {
                    mutableStateOf(false)
                }

                NeuSwitch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            }
        }
    }
}