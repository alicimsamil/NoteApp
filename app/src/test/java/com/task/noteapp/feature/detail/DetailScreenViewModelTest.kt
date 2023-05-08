package com.task.noteapp.feature.detail

import com.task.noteapp.MainCoroutineRule
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.FakeNoteRepositoryImpl
import com.task.noteapp.domain.usecase.GetNoteByIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailScreenViewModel: DetailScreenViewModel

    @Before
    fun setup() {
        detailScreenViewModel =
            DetailScreenViewModel(GetNoteByIdUseCase(FakeNoteRepositoryImpl()))
    }

    @Test
    fun test_on_event() = runTest {
        detailScreenViewModel.onEvent(DetailScreenUiEvents.getNote(1))
        delay(1000)
        assertEquals(detailScreenViewModel.uiState.value?.isLoading, false)
    }

    @Test
    fun test_get_note() = runTest {
        val model =
            NoteModel(
                "1",
                87897878,
                "Title",
                "Description",
                id = 1
            )
        detailScreenViewModel.uiState.value = DetailScreenUiState(model)

        detailScreenViewModel.getNote(1)

        assertEquals(false, detailScreenViewModel.uiState.value?.isLoading)
        assertEquals(
            "Title",
            (detailScreenViewModel.uiState.value as DetailScreenUiState).data?.noteTitle
        )
        assertEquals(
            "Description",
            (detailScreenViewModel.uiState.value as DetailScreenUiState).data?.note
        )
    }

}