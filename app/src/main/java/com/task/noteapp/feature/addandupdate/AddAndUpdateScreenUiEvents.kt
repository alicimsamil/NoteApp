package com.task.noteapp.feature.addandupdate

import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.data.model.NoteModel

sealed class AddAndUpdateScreenUiEvents : UiEvent {
    data class UpdateNote(val noteModel: NoteModel) : AddAndUpdateScreenUiEvents()
    data class CreateNote(val noteModel: NoteModel) : AddAndUpdateScreenUiEvents()
}