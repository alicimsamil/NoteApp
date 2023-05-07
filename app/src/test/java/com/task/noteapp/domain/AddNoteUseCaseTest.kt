package com.task.noteapp.domain

import com.task.noteapp.core.data.Error
import com.task.noteapp.core.data.Success
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.FakeNoteRepositoryImpl
import com.task.noteapp.domain.usecase.AddNoteUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var repository: FakeNoteRepositoryImpl
    private lateinit var useCase: AddNoteUseCase

    @Before
    fun setup() {
        repository = FakeNoteRepositoryImpl()
        useCase = AddNoteUseCase(repository)
    }

    @Test
    fun add_note_should_be_success() = runTest {
        val note = NoteModel(
            imageUrl = "www.test.com",
            date = 214513552125,
            noteTitle = "Title",
            note = "Note",
            editedTag = false,
            id = 1
        )
        val result = useCase.invoke(note)
        assertEquals(Success(Unit), result)
        assertTrue(repository.notes.contains(note))
    }


    @Test
    fun add_note_with_empty_title_should_be_fail() = runTest {
        val note = NoteModel(
            imageUrl = "www.test.com",
            date = 214513552125,
            noteTitle = "",
            note = "Note",
            editedTag = false,
            id = 1
        )
        val result = useCase.invoke(note)
        assertEquals(Error("Please check title and note areas."), result)
    }


}