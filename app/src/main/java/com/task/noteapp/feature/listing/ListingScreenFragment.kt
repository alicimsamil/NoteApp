package com.task.noteapp.feature.listing

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.task.noteapp.core.ui.BaseFragment
import com.task.noteapp.databinding.FragmentListingScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingScreenFragment :
    BaseFragment<FragmentListingScreenBinding, ListingScreenViewModel>(FragmentListingScreenBinding::inflate) {

    override val viewModel by viewModels<ListingScreenViewModel>()

    private val pagingAdapter = NotesAdapter(NotesAdapter.NoteComparator)

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

}