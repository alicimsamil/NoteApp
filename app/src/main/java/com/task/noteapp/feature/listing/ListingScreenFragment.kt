package com.task.noteapp.feature.listing

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.core.ui.BaseFragment
import com.task.noteapp.core.ui.custom.CallBack
import com.task.noteapp.core.ui.custom.DeleteNoteDialog
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.FragmentListingScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingScreenFragment :
    BaseFragment<FragmentListingScreenBinding, ListingScreenViewModel>(FragmentListingScreenBinding::inflate), OnClickListener {

    override val viewModel by viewModels<ListingScreenViewModel>()

    private val pagingAdapter = NotesAdapter(NotesAdapter.NoteComparator, this)

    override var state = ListingUiState()

    override fun initialize() {
        super.initialize()
        observeUiState()
        setRecyclerAdapter()
    }

    private fun setRecyclerAdapter() {
        binding.rvNotes.adapter = pagingAdapter
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    state = it as ListingUiState
                    handleFailure()
                    handleLoading()
                    state.data?.collectLatest {
                        pagingAdapter.submitData(it)
                    }
                }
            }
        }
    }

    override fun delete(noteModel: NoteModel) {
        DeleteNoteDialog(object : CallBack{
            override fun delete() {
                viewModel.onEvent(ListingUiEvents.DeleteNote(noteModel))
            }

        }).show(parentFragmentManager,"DeleteNoteDialog")
    }

    override fun update(noteModel: NoteModel) {
    }

    override fun goDetail(id: Int?) {
        id?.let {
            navigate(R.id.action_listingScreenFragment_to_detailScreenFragment ,bundleOf("id" to id))
        }
    }

}