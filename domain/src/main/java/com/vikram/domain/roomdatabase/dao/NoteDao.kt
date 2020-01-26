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

    /**
     * Add the note to data base
     *and will return its id.
     * @param note
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note): Long

    /**
     * this function add multiple notes
     *and will return the list consisting of ids of
     * notes.
     * @param notes
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMultipleNotes(notes: List<Note>):List<Long>
}