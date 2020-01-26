package com.vikram.domain.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Note.NOTE)
data class Note(
    val title: String,
    val content: String,
    val date : Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        const val NOTE = "note_table"
    }
}