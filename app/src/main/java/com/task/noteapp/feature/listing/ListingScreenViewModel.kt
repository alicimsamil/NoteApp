package com.task.noteapp.feature.listing

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.noteapp.core.ui.BaseViewModel
import com.task.noteapp.core.ui.UiEvent
import com.task.noteapp.domain.usecase.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingScreenViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) :
    BaseViewModel() {

    init {
        _uiState.value = ListingUiState()
        getAllNotes()
    }


    override fun onEvent(event: UiEvent) {
        TODO("Not yet implemented")
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            _uiState.value = (_uiState.value as ListingUiState).copy(isLoading = true)
            getAllNotesUseCase.invoke()
                .onSuccess { pagingSource ->
                    _uiState.value = (_uiState.value as ListingUiState).copy(
                        data =
                        Pager(
                            PagingConfig(
                                pageSize = 10,
                                enablePlaceholders = false
                            )
                        ) {
                            pagingSource!!
                        }.flow.cachedIn(CoroutineScope(Dispatchers.IO)),
                        isLoading = false
                    )
                }
                .onFailure {
                    it?.let {
                        _uiState.value =
                            (_uiState.value as ListingUiState).copy(error = it, isLoading = false)
                    }
                }
        }
    }
}