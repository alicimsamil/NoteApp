package com.task.noteapp.data.local.source

import com.task.noteapp.core.data.BaseLocalDataSource
import com.task.noteapp.data.local.db.NoteDAO
import com.task.noteapp.data.model.NoteModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val noteDAO: NoteDAO) : BaseLocalDataSource() {

    suspend fun addNote(noteModel: NoteModel) =
        performDatabaseOperation {
            noteDAO.addNote(noteModel)
        }

    suspend fun updateNote(noteModel: NoteModel) =
        performDatabaseOperation {
            noteDAO.updateNote(noteModel)
        }

    suspend fun deleteNote(noteModel: NoteModel) =
        performDatabaseOperation {
            noteDAO.deleteNote(noteModel)
        }

    suspend fun getAllNotes() =
        performDatabaseOperation {
            noteDAO.getAllNotes()
        }

    suspend fun getNoteById(id: Int) =
        performDatabaseOperation {
            noteDAO.getNoteById(id)
        }

}