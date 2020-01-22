package com.vikram.mynotes.repositories

import com.vikram.domain.roomdatabase.models.Note

interface NotesRepository {
    suspend fun getAllNotes():List<Note>
    suspend fun saveNote(note:Note)
}