package com.example.neumorphic.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUIState(
    val title : String = "Home"
)

sealed interface HomeEvent{
    data object ToSecond : HomeEvent
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()
}