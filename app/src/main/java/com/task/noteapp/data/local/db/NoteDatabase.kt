package com.task.noteapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.BuildConfig
import com.task.noteapp.data.model.NoteModel

@Database(entities = [NoteModel::class], version = BuildConfig.VERSION_CODE)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO
}