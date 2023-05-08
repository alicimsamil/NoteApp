package com.task.noteapp.data.repository.local

import com.task.noteapp.data.local.source.LocalDataSource
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.repository.local.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    NoteRepository {
    override suspend fun addNote(noteModel: NoteModel) =
        localDataSource.addNote(noteModel)

    override suspend fun updateNote(noteModel: NoteModel) =
        localDataSource.updateNote(noteModel)

    override suspend fun deleteNote(noteModel: NoteModel) =
        localDataSource.deleteNote(noteModel)

    override fun getAllNotes() =
        localDataSource.getAllNotes()

    override suspend fun getNoteById(id: Int) =
        localDataSource.getNoteById(id)
}