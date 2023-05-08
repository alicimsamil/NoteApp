package com.task.noteapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.noteapp.data.model.NoteModel

class FakePagingSource : PagingSource<Int, NoteModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoteModel> {
        if(params.key == null){
            val firstPage = listOf<NoteModel>()
            val nextKey  = 2
                return LoadResult.Page(
                    data = firstPage ,
                    prevKey = null,
                    nextKey = nextKey
                )
        } else{
            val newPage = listOf<NoteModel>()
            val nextKey  = 2
                return LoadResult.Page(
                    data = newPage ,
                    prevKey = null,
                    nextKey = nextKey
                )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NoteModel>): Int {
        return 0
    }
}