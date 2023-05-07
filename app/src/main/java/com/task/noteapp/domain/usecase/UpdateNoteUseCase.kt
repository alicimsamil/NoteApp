package com.task.noteapp.domain.usecase

import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.repository.local.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(noteModel: NoteModel)  = repository.updateNote(noteModel)
}