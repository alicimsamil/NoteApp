package com.task.noteapp.core.ui

data class TestUiState(
    val data: String,
    override var isLoading: Boolean,
    override var error: String
) : UiState
