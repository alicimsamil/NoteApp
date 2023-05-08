package com.task.noteapp.core.ui.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.task.noteapp.databinding.DialogDeleteNoteBinding

interface CallBack{
    fun delete()
}

class DeleteNoteDialog(private val callBack: CallBack) : DialogFragment() {

    private lateinit var binding: DialogDeleteNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDeleteNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDeleteNoteDialogNo.setOnClickListener {
            dismiss()
        }

        binding.btnDeleteNoteDialogYes.setOnClickListener {
            callBack.delete()
            dismiss()
        }
    }

}