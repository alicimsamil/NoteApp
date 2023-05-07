package com.task.noteapp.domain.usecase

import com.task.noteapp.core.data.Error
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.repository.local.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(noteModel: NoteModel) = run {
        if (noteModel.noteTitle.isNotEmpty() && noteModel.note.isNotEmpty()) {
            repository.addNote(noteModel)
        } else {
            Error("Please check title and note areas.")
        }
    }

}