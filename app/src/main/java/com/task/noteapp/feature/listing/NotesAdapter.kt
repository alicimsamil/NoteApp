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

class NotesAdapter(diffCallback: NoteComparator) :
    PagingDataAdapter<NoteModel, NoteViewHolder>(diffCallback) {

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
}

class NoteViewHolder(private val binding: PagingNoteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(noteModel: NoteModel?) {
        binding.apply {
            noteModel?.let {
                it.imageUrl?.let { it1 -> ivNoteItem.loadImage(it1) }
                tvNoteItemDate.text = noteModel.date.formatDate()
                tvNoteItemTitle.text = noteModel.noteTitle
                tvNoteItemDesc.text = noteModel.note
                if (noteModel.editedTag) tvNoteItemEditedTag.visible() else tvNoteItemEditedTag.gone()
            }
        }
    }
}
