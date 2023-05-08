package com.task.noteapp.feature.listing

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.noteapp.core.ui.BaseViewModel
import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.usecase.DeleteNoteUseCase
import com.task.noteapp.domain.usecase.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingScreenViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) :
    BaseViewModel() {

    init {
        _uiState.value = ListingUiState()
        getAllNotes()
    }


    override fun onEvent(event: UiEvent) {
        when (event) {
            is ListingUiEvents.DeleteNote -> {
                deleteNote(event.noteModel)
            }
        }
    }

    private fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            _uiState.value = (_uiState.value as ListingUiState).copy(isLoading = true)
            deleteNoteUseCase.invoke(noteModel)
                .onSuccess {
                    _uiState.value = (_uiState.value as ListingUiState).copy(isLoading = false)
                }
                .onFailure {
                    _uiState.value =
                        (_uiState.value as ListingUiState).copy(
                            isLoading = false,
                            error = it.orEmpty()
                        )
                }
        }
    }


    private fun getAllNotes() {
        _uiState.value = (_uiState.value as ListingUiState).copy(isLoading = true)
        _uiState.value = (_uiState.value as ListingUiState).copy(
            data = Pager(
                PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { getAllNotesUseCase.invoke() }
            ).flow.cachedIn(viewModelScope)
                .catch { exception ->
                    _uiState.value = (_uiState.value as ListingUiState).copy(
                        error = exception.message.orEmpty(),
                        isLoading = false
                    )
                }
            ,
            isLoading = false
        )
    }
}