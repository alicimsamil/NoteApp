package com.task.noteapp.di

import com.task.noteapp.data.repository.local.NoteRepositoryImpl
import com.task.noteapp.domain.repository.local.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}