package com.task.noteapp.feature.detail

import androidx.lifecycle.viewModelScope
import com.task.noteapp.core.ui.BaseViewModel
import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.domain.usecase.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val getNoteByIdUseCase: GetNoteByIdUseCase) :
    BaseViewModel() {

    init {
        _uiState.value = DetailScreenUiState()
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is DetailScreenUiEvents.getNote -> {
                getNote(event.id)
            }
        }
    }

    fun getNote(id: Int) {
        viewModelScope.launch {
            _uiState.value = (_uiState.value as DetailScreenUiState).copy(isLoading = true)

            getNoteByIdUseCase.invoke(id)
                .onSuccess {
                    _uiState.value = (_uiState.value as DetailScreenUiState).copy(data = it)
                }
                .onFailure {
                    _uiState.value = (_uiState.value as DetailScreenUiState).copy(isLoading = false, error = it.orEmpty())
                }
        }
    }
}