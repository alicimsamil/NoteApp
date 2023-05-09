package com.task.noteapp.feature.addandupdate

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.task.noteapp.R
import com.task.noteapp.core.ui.BaseFragment
import com.task.noteapp.core.ui.UiState
import com.task.noteapp.core.ui.custom.NoteAppButton
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.FragmentAddAndUpdateScreenBinding
import com.task.noteapp.feature.detail.DetailScreenUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddAndUpdateScreenFragment :
    BaseFragment<FragmentAddAndUpdateScreenBinding, AddAndUpdateScreenViewModel>(
        FragmentAddAndUpdateScreenBinding::inflate
    ) {
    override val viewModel by viewModels<AddAndUpdateScreenViewModel>()
    override var state = AddAndUpdateScreenUiState()
    private val args by navArgs<AddAndUpdateScreenFragmentArgs>()
    private lateinit var saveNoteAppButton: NoteAppButton
    var pType: PageType? = null

    override fun initialize() {
        super.initialize()
        setPageType()
        initCustomButton()
        setUI()
        observeUiState()
        initListeners()
    }

    private fun setPageType() {
        args.model?.let {
            pType = PageType.UPDATE
        } ?: run {
            pType = PageType.CREATE
        }
    }

    private fun initCustomButton() {
        saveNoteAppButton = binding.btnSave
        lifecycle.addObserver(saveNoteAppButton)
    }

    private fun setUI() {
        if (pType == PageType.CREATE) {
            binding.apply {
                tvAddNoteTitle.text = getString(R.string.save_note)
                btnSave.apply {
                    text = getString(R.string.save_note)
                    setIcon(getDrawable(context, R.drawable.save_48px))
                }
            }
        } else {
            args.model?.let {
                binding.apply {
                    tvAddNoteTitle.text = getString(R.string.update_note)
                    tilImageUrl.editText?.setText(it.imageUrl)
                    tilNote.editText?.setText(it.note)
                    tilTitle.editText?.setText(it.noteTitle)
                    btnSave.text = getString(R.string.update)
                    btnSave.setIcon(context?.let { getDrawable(it, R.drawable.upgrade_48px) })
                }
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    state = it as AddAndUpdateScreenUiState
                    handleLoading()
                    handleFailure()
                    if (state.addNoteSuccessState || state.updateNoteSuccessState) {
                        navigate(R.id.action_addAndUpdateScreenFragment_to_listingScreenFragment)
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if (pType == PageType.CREATE) {
                viewModel.onEvent(AddAndUpdateScreenUiEvents.CreateNote(getNoteModel()))
            } else {
                viewModel.onEvent(AddAndUpdateScreenUiEvents.UpdateNote(getNoteModel()))
            }
        }
    }

    private fun getNoteModel(): NoteModel {
        return NoteModel(
            binding.tilImageUrl.editText?.text.toString(),
            if (pType==PageType.CREATE) System.currentTimeMillis() else args.model?.date ?:0,
            binding.tilTitle.editText?.text.toString(),
            binding.tilNote.editText?.text.toString(),
            pType != PageType.CREATE,
            if (pType==PageType.CREATE) null else args.model?.id ?:0
        )
    }
}

enum class PageType { UPDATE, CREATE }
