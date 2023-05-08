package com.task.noteapp.feature.listing

import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.data.model.NoteModel

sealed class ListingUiEvents : UiEvent {
    data class DeleteNote(val noteModel: NoteModel) : ListingUiEvents()
}
