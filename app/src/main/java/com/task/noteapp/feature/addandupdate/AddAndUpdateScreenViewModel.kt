package com.task.noteapp.feature.addandupdate

import androidx.lifecycle.viewModelScope
import com.task.noteapp.core.ui.BaseViewModel
import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.usecase.AddNoteUseCase
import com.task.noteapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAndUpdateScreenViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : BaseViewModel() {

    init {
        _uiState.value = AddAndUpdateScreenUiState()
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is AddAndUpdateScreenUiEvents.CreateNote -> {
                addNote(event.noteModel)
            }
            is AddAndUpdateScreenUiEvents.UpdateNote -> {
                updateNote(event.noteModel)
            }
        }
    }

    fun addNote(noteModel: NoteModel) {
        viewModelScope.launch {
            _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = true)

            addNoteUseCase.invoke(noteModel)
                .onSuccess {
                    _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = false, addNoteSuccessState = true)
                }
                .onFailure {
                    _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = false, error = it.orEmpty())
                }
        }
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = true)
            updateNoteUseCase.invoke(noteModel)
                .onSuccess {
                    _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = false, updateNoteSuccessState = true)
                }
                .onFailure {
                    _uiState.value = (_uiState.value as AddAndUpdateScreenUiState).copy(isLoading = false, error = it.orEmpty())
                }
        }
    }
}