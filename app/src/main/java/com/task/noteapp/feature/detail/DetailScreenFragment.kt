package com.task.noteapp.feature.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.task.noteapp.R
import com.task.noteapp.core.ui.BaseFragment
import com.task.noteapp.databinding.FragmentDetailScreenBinding
import com.task.noteapp.util.extensions.formatDate
import com.task.noteapp.util.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment :
    BaseFragment<FragmentDetailScreenBinding, DetailScreenViewModel>(FragmentDetailScreenBinding::inflate) {
    override val viewModel by viewModels<DetailScreenViewModel>()
    override var state = DetailScreenUiState()
    private val args by navArgs<DetailScreenFragmentArgs>()

    override fun initialize() {
        super.initialize()
        getNote()
        observeUiState()
        initListeners()
    }

    private fun getNote() {
        viewModel.onEvent(DetailScreenUiEvents.getNote(args.id))
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    state = it as DetailScreenUiState
                    handleLoading()
                    handleFailure()
                    setUI()
                }
            }
        }
    }

    private fun setUI() {
        state.data?.let { noteModel ->
            binding.apply {
                noteModel.imageUrl?.let {
                    ivDetailNoteItem.loadImage(noteModel.imageUrl)
                }
                tvDetailNoteItemDate.text = noteModel.date.formatDate()
                tvDetailNoteItemTitle.text = noteModel.noteTitle
                tvDetailNoteItemDesc.text = noteModel.note
            }
        }
    }

    private fun initListeners() {
        binding.ivDetailBack.setOnClickListener {
            navigate(R.id.action_detailScreenFragment_to_listingScreenFragment)
        }
    }
}