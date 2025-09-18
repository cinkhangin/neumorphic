@file:Suppress("unused")

package com.naulian.neumorphic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
internal fun NeumorphicPreviewSquare(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    NeumorphicPreview(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            content = content
        )
    }
}

@Composable
internal fun NeumorphicPreviewBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    NeumorphicPreview(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
internal fun NeumorphicPreviewColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    NeumorphicPreview(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            content = content
        )
    }
}


@Composable
internal fun NeumorphicPreviewScreen(
    modifier: Modifier = Modifier,
    dark : Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    NeumorphicPreview(
        dark = dark,
        modifier = modifier.fillMaxSize(),
        content = content
    )
}

@Composable
private fun NeumorphicPreview(
    modifier: Modifier = Modifier,
    dark: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    ComposableTheme(darkTheme = dark) {
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(20.dp),
            content = content,
            contentAlignment = contentAlignment
        )
    }
}