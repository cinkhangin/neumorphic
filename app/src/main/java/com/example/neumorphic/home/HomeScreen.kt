package com.example.neumorphic.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.neumorphic.LocalNavController
import com.example.neumorphic.Screen

@Composable
fun HomeScreen() {

    val navController = LocalNavController.current

    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeEvent.ToSecond -> navController.navigate(Screen.Second)
            }
        }
    }

    HomeScreenUI(uiState = state) {
        when (it) {
            HomeUIEvent.Back -> navController.navigateUp()
        }
    }
}