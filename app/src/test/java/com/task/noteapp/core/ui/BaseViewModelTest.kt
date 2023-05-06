package com.task.noteapp.core.ui


import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class BaseViewModelTest {

    private lateinit var viewModel: BaseViewModel

    @Before
    fun setup() {
        viewModel = object : BaseViewModel() {
            override fun onEvent(event: UiEvent) {}
        }
    }

    @Test
    fun uistate_should_be_update_correctly() = runTest {
        assertNull(viewModel.uiState.value?.isLoading)
        val uiState = UiState(isLoading = false)
        viewModel.uiState.emit(uiState)
        assertEquals(uiState.isLoading, viewModel.uiState.value?.isLoading)
    }

}