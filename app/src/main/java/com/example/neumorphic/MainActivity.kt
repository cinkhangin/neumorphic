package com.example.neumorphic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ckgin.modify.HugeIcons
import com.ckgin.neumorphic.CarbonFiberTexture
import com.ckgin.neumorphic.CharcoalGlitterTexture
import com.ckgin.neumorphic.ConcreteTexture
import com.ckgin.neumorphic.GoldTexture
import com.ckgin.neumorphic.LeatherTexture
import com.ckgin.neumorphic.MarbleTexture
import com.ckgin.neumorphic.NeumorphicTheme
import com.ckgin.neumorphic.RadialSteelTexture
import com.ckgin.neumorphic.SilverGlitterTexture
import com.ckgin.neumorphic.WoodTexture
import com.ckgin.neumorphic.demo.SquarcleShape
import com.ckgin.neumorphic.neumorphicUp
import com.ckgin.neumorphic.new.NeuButton
import com.ckgin.neumorphic.new.NeuIconButton
import com.ckgin.neumorphic.new.NeuProgressIndicatorBar
import com.ckgin.neumorphic.new.NeuRadioButton
import com.ckgin.neumorphic.new.NeuSwitch
import com.ckgin.neumorphic.new.NeumorphicCheckBox
import com.ckgin.neumorphic.texture

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            NeumorphicTheme {
                ShowcaseScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                navigationIcon = {
                    NeuIconButton(
                        onClick = {},
                        shape = CircleShape
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(HugeIcons.Account),
                            contentDescription = "Account"
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Neumorphic UIs",
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeumorphicTheme.colorScheme.background
                ),
                actions = {
                    NeuIconButton(
                        onClick = {},
                        shape = SquarcleShape(n = 3f)
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(HugeIcons.Notification),
                            contentDescription = "Account"
                        )
                    }
                }
            )
        },
        containerColor = NeumorphicTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Components",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NeuButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Button", modifier = Modifier.padding(vertical = 8.dp))
                }

                NeuButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = CircleShape
                ) {
                    Text("Circle", modifier = Modifier.padding(vertical = 8.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var switchChecked by remember { mutableStateOf(true) }
                NeuSwitch(checked = switchChecked, onCheckedChange = { switchChecked = it })

                var checkBoxChecked by remember { mutableStateOf(true) }
                NeumorphicCheckBox(
                    checked = checkBoxChecked,
                    onCheckedChange = { checkBoxChecked = it })

                var radioSelected by remember { mutableStateOf(true) }
                NeuRadioButton(
                    checked = radioSelected,
                    onCheckedChange = { radioSelected = it })
            }

            NeuProgressIndicatorBar(
                modifier = Modifier.fillMaxWidth()
                    .height(10.dp),
                shape = CircleShape
            )

            Text(
                "Textures",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            val textures = listOf(
                "Radial Steel" to RadialSteelTexture(),
                "Silver Glitter" to SilverGlitterTexture(),
                "Charcoal" to CharcoalGlitterTexture(),
                "Concrete" to ConcreteTexture(),
                "Gold" to GoldTexture(),
                "Marble" to MarbleTexture(),
                "Leather" to LeatherTexture(),
                "Wood" to WoodTexture(),
                "Carbon Fiber" to CarbonFiberTexture()
            )

            // Using a regular Column/Row approach instead of LazyVerticalGrid inside a scrollable Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                textures.chunked(3).forEach { rowTextures ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowTextures.forEach { (name, texture) ->
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .fillMaxWidth()
                                        .texture(texture, RoundedCornerShape(20.dp))
                                )
                                Text(
                                    name,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onBackground.copy(
                                        alpha = 0.7f
                                    ),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        // Fill empty spaces if row has less than 3 items
                        repeat(3 - rowTextures.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ShowcaseScreenPreview() {
    NeumorphicTheme {
        ShowcaseScreen()
    }
}
