package com.task.noteapp.feature.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.PagingNoteItemBinding
import com.task.noteapp.util.extensions.formatDate
import com.task.noteapp.util.extensions.gone
import com.task.noteapp.util.extensions.loadImage
import com.task.noteapp.util.extensions.visible

interface OnClickListener {
    fun delete(noteModel: NoteModel)
    fun update(noteModel: NoteModel)
    fun goDetail(id: Int?)
}

class NotesAdapter (diffCallback: NoteComparator, private val onClickListener: OnClickListener) :
    PagingDataAdapter<NoteModel, NotesAdapter.NoteViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            PagingNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    object NoteComparator : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class NoteViewHolder(private val binding: PagingNoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: NoteModel?) {
            binding.apply {
                noteModel?.let {
                    noteModel.imageUrl?.let { ivNoteItem.loadImage(it) }
                    tvNoteItemDate.text = noteModel.date.formatDate()
                    tvNoteItemTitle.text = noteModel.noteTitle
                    tvNoteItemDesc.text = noteModel.note
                    if (noteModel.editedTag) tvNoteItemEditedTag.visible() else tvNoteItemEditedTag.gone()
                    initListeners(noteModel)
                }
            }
        }

        private fun initListeners(noteModel: NoteModel) {
            binding.apply {
                ivDeleteItem.setOnClickListener {
                    onClickListener.delete(noteModel)
                }
                ivEditItem.setOnClickListener {
                    onClickListener.update(noteModel)
                }
                root.setOnClickListener {
                    onClickListener.goDetail(noteModel.id)
                }
            }
        }
    }
}

