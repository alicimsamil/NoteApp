package com.task.noteapp.feature.addandupdate

import com.task.noteapp.core.ui.UiState

data class AddAndUpdateScreenUiState(
    var addNoteSuccessState : Boolean = false,
    var updateNoteSuccessState : Boolean = false,
    override var isLoading: Boolean = false,
    override var error: String = ""
) : UiState
