package com.task.noteapp.domain

import com.task.noteapp.core.data.Success
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.FakeNoteRepositoryImpl
import com.task.noteapp.domain.usecase.DeleteNoteUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {

    private lateinit var repository: FakeNoteRepositoryImpl
    private lateinit var useCase: DeleteNoteUseCase
    private lateinit var note : NoteModel

    @Before
    fun setup() {
        repository = FakeNoteRepositoryImpl()
        useCase = DeleteNoteUseCase(repository)
        note = NoteModel(
            imageUrl = "www.test.com",
            date = 214513552125,
            noteTitle = "Title",
            note = "Note",
            editedTag = false,
            id = 1
        )
    }

    @Test
    fun delete_note_should_be_success() = runTest {
        repository.addNote(note)
        Assert.assertTrue(repository.notes.contains(note))
        val result = useCase.invoke(note)
        Assert.assertEquals(Success(Unit), result)
        Assert.assertFalse(repository.notes.contains(note))
    }

}