package com.task.noteapp.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.data.local.db.NoteDAO
import com.task.noteapp.data.local.db.NoteDatabase
import com.task.noteapp.data.model.NoteModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NoteDAOTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("TestDatabase")
    lateinit var noteDatabase: NoteDatabase

    private lateinit var noteDAO: NoteDAO

    private lateinit var noteModel: NoteModel

    @Before
    fun setup() {
        hiltRule.inject()
        noteDAO = noteDatabase.noteDAO()
        val date = System.currentTimeMillis()
        noteModel = NoteModel(
            imageUrl = "www.test.com",
            date = date,
            noteTitle = "Title",
            note = "Note",
            editedTag = false,
            id = 1
        )
    }

    @Test
    fun note_should_be_insert_correctly() = runBlocking {
        noteDAO.addNote(noteModel)
        val noteFromDB = noteDAO.getNoteById(1)
        assertEquals(noteModel, noteFromDB)
    }

    @Test
    fun note_should_be_update_correctly() = runBlocking {
        noteDAO.addNote(noteModel)
        val updateNoteModel = NoteModel(
            imageUrl = "www.google.com",
            date = 123,
            noteTitle = "Title",
            note = "Note",
            editedTag = false,
            id = 1
        )
        noteDAO.updateNote(updateNoteModel)
        val noteFromDB = noteDAO.getNoteById(1)
        assertEquals(noteFromDB, updateNoteModel)
    }

    @Test
    fun note_should_be_delete_correctly() = runBlocking {
        noteDAO.addNote(noteModel)
        var noteFromDB = noteDAO.getNoteById(1)
        assertEquals(noteFromDB, noteModel)
        noteDAO.deleteNote(noteModel)
        noteFromDB = noteDAO.getNoteById(1)
        assertNull(noteFromDB)
    }

    @Test
    fun get_note_by_id_should_be_correctly_run() = runBlocking {
        noteDAO.addNote(noteModel)
        val noteFromDB = noteDAO.getNoteById(1)
        assertEquals(noteFromDB, noteModel)
    }

    @After
    fun teardown() {
        noteDatabase.close()
    }

}