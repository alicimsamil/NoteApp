package com.task.noteapp.domain.usecase

import com.task.noteapp.domain.repository.local.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int)  = repository.getNoteById(id)
}