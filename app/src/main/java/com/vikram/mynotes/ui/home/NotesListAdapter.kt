package com.vikram.mynotes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.databinding.ItemNoteBinding
import com.vikram.mynotes.util.NoteDiffUtil
import com.vikram.mynotes.util.toDate

class NotesListAdapter() : RecyclerView.Adapter<NotesListAdapter.NoteItemViewHolder>() {

    private val noteList: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteItemViewHolder(binding)
    }

    override fun getItemCount(): Int = noteList.count()

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bindData(noteList[position])
    }

    fun updateNotesList(noteList: List<Note>) {
        val result: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(NoteDiffUtil(noteList, this.noteList))
        result.dispatchUpdatesTo(this)
        this.noteList.apply {
            clear()
            addAll(noteList)
        }
    }

    class NoteItemViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(note: Note) {
            binding.run {
                tvContent.text = note.content
                tvTitle.text = note.title
                tvDate.text = note.date.toDate()
            }
        }
    }

}