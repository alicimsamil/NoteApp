package com.task.noteapp.domain.repository.local

import androidx.paging.PagingSource
import com.task.noteapp.core.data.DataResult
import com.task.noteapp.data.model.NoteModel

interface NoteRepository {
    suspend fun addNote(noteModel: NoteModel): DataResult<Unit, String>
    suspend fun updateNote(noteModel: NoteModel): DataResult<Unit, String>
    suspend fun deleteNote(noteModel: NoteModel): DataResult<Unit, String>
    suspend fun getAllNotes(): DataResult<PagingSource<Int, NoteModel>, String>
    suspend fun getNoteById(id: Int): DataResult<NoteModel, String>
}