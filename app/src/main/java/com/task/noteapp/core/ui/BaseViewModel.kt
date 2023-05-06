package com.task.noteapp.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _uiState = MutableStateFlow<UiState?>(null)
    val uiState = _uiState

    abstract fun onEvent(event: UiEvent)

}