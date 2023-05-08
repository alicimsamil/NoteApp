package com.task.noteapp.feature.main

import com.task.noteapp.core.ui.UiState

data class MainUiState(
    val splashAnimationState: Boolean = true,
    override var isLoading: Boolean = false,
    override var error: String = ""
) : UiState
