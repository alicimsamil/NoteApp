package com.task.noteapp.feature.detail

import com.task.noteapp.core.ui.UiState
import com.task.noteapp.data.model.NoteModel

data class DetailScreenUiState(
    val data: NoteModel? = null,
    override var isLoading: Boolean = false,
    override var error: String = ""
) : UiState
