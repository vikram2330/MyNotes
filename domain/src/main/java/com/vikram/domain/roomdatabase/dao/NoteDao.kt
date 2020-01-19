package com.vikram.domain.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vikram.domain.roomdatabase.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${Note.NOTE}")
    suspend fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMultipleNotes(notes: List<Note>)
}