package com.task.noteapp.feature.detail

import com.task.noteapp.core.ui.UiEvent

sealed class DetailScreenUiEvents : UiEvent {
    data class getNote(val id: Int) : DetailScreenUiEvents()
}
