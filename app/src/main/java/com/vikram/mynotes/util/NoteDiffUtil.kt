package com.vikram.mynotes.util

import androidx.recyclerview.widget.DiffUtil
import com.vikram.domain.roomdatabase.models.Note

class NoteDiffUtil(
    private val newList: List<Note>,
    private val oldList: List<Note>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].run {
            val newNote = newList[newItemPosition]
            (title == newNote.title && content == newNote.content)
        }
    }
}