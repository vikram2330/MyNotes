package com.vikram.mynotes.repositories

import com.vikram.domain.roomdatabase.dao.NoteDao
import com.vikram.domain.roomdatabase.models.Note

class NotesRepositoryImpl(private val dao: NoteDao) : NotesRepository {

    override suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }

    override suspend fun saveNote(note: Note): Long {
        return dao.addNote(note)
    }

    override suspend fun getNoteById(noteId: Long): Note {
        return dao.getNoteWithId(noteId)
    }


}