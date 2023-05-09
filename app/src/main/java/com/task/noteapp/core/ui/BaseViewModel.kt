package com.task.noteapp.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This viewmodel collects the basic functions done in other viewmodels.
 * Other view models must inherit this viewmodel.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * This ui state is the state that fragments should use in common.
     */
    protected val _uiState = MutableStateFlow<UiState?>(null)
    val uiState = _uiState

    /**
     * The onEvent function is written to manage the
     * communication between fragment and viewmodel from one place.
     */
    abstract fun onEvent(event: UiEvent)

}