package com.ckgin.neumorphic.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ckgin.neumorphic.NeumorphicTheme
import com.ckgin.neumorphic.new.NeuButton

@Composable
fun NeuTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
) {

    //todo:implement
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .neuEdgeUp(CircleShape)
    ) {

    }
}

@Preview
@Composable
private fun NeuTopBarPreview() {
    NeumorphicTheme {
        Scaffold(
            topBar = {
                NeuTopBar(
                    title = {
                        Text("Yello World")
                    },
                )
            },
            containerColor = NeumorphicTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NeuButton(modifier = Modifier.height(54.dp), onClick = {}) {
                    Text("Yello World")
                }
            }
        }
    }
}