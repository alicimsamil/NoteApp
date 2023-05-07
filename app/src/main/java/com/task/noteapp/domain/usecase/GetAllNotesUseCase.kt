package com.task.noteapp.domain.usecase

import com.task.noteapp.domain.repository.local.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke()  = repository.getAllNotes()
}