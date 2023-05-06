package com.task.noteapp.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.model.TableConstants.TABLE_NAME

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllNotes(): PagingSource<Int, NoteModel>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteModel
}