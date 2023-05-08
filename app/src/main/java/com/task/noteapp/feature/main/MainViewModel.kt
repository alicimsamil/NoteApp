package com.task.noteapp.feature.main

import androidx.lifecycle.viewModelScope
import com.task.noteapp.core.ui.BaseViewModel
import com.task.noteapp.core.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    init {
        _uiState.value = MainUiState()
        getSplashAnimationState()
    }

    override fun onEvent(event: UiEvent) {}

    private fun getSplashAnimationState() {
        viewModelScope.launch {
            delay(750.milliseconds)
            _uiState.value = (_uiState.value as MainUiState).copy(splashAnimationState = false)
        }
    }
}