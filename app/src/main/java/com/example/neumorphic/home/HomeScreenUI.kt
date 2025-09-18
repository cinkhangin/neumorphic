package com.example.neumorphic.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.naulian.modify.HugeIcons

sealed interface HomeUIEvent {
    data object Back : HomeUIEvent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    uiState: HomeUIState = HomeUIState(),
    uiEvent: (HomeUIEvent) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        uiEvent(HomeUIEvent.Back)
                    }) {
                        Icon(
                            painter = painterResource(id = HugeIcons.Back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { screenPadding ->
        LazyColumn(
            modifier = Modifier.padding(screenPadding)
        ) {

        }
    }
}