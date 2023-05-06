package com.task.noteapp.di

import android.content.Context
import androidx.room.Room
import com.task.noteapp.BuildConfig
import com.task.noteapp.data.local.db.NoteDAO
import com.task.noteapp.data.local.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteDAO(noteDatabase: NoteDatabase): NoteDAO =
        noteDatabase.noteDAO()
}