package com.vikram.mynotes.repositories

import com.vikram.domain.roomdatabase.dao.NoteDao
import com.vikram.domain.roomdatabase.models.Note

class NotesRepositoryImpl(private val dao: NoteDao) : NotesRepository {

    override suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }

    override suspend fun saveNote(note: Note) {
        dao.addNote(note)
    }


}