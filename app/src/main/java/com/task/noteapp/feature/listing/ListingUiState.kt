package com.task.noteapp.feature.listing

import androidx.paging.PagingData
import com.task.noteapp.core.ui.UiState
import com.task.noteapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

data class ListingUiState(
    val data: Flow<PagingData<NoteModel>>? = null,
    override var isLoading: Boolean = false,
    override var error: String = ""
) : UiState
