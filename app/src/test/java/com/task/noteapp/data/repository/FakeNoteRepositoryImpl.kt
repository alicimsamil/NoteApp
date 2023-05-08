package com.task.noteapp.data.repository

import androidx.paging.PagingSource
import com.task.noteapp.core.data.DataResult
import com.task.noteapp.core.data.Error
import com.task.noteapp.core.data.Success
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.repository.local.NoteRepository

class FakeNoteRepositoryImpl : NoteRepository {
    val notes = mutableListOf<NoteModel>()

    override suspend fun addNote(noteModel: NoteModel): DataResult<Unit, String> {
        notes.add(noteModel)
        return Success(Unit)
    }

    override suspend fun updateNote(noteModel: NoteModel): DataResult<Unit, String> {
        notes.forEachIndexed { index, data->
            if (data.id == noteModel.id){
                notes[index] = noteModel
                return Success(Unit)
            }
        }
        return Error("Note not found")
    }

    override suspend fun deleteNote(noteModel: NoteModel): DataResult<Unit, String> {
        if (notes.remove(noteModel)) {
            return Success(Unit)
        }
        return Error("Note not found")
    }

    override fun getAllNotes(): PagingSource<Int, NoteModel> {
        return FakePagingSource()
    }

    override suspend fun getNoteById(id: Int): DataResult<NoteModel, String> {
        val note = notes.find { it.id == id }
        return if (note != null) {
            Success(note)
        } else {
            Error("Note not found")
        }
    }

}