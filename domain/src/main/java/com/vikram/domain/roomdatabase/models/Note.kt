package com.vikram.domain.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Note.NOTE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String
) {
    companion object {
        const val NOTE = "note_table"
    }
}