package com.vikram.mynotes.usecases

import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.repositories.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NotesRepository) :
    BaseUseCase<Long, Note> {
    /**
     * Add the note in database
     * throws @exception if empty data passes in any field
     */
    override suspend fun perform(params: Note): Long {
        validateNote(params)
        return repository.saveNote(params)
    }


    private fun validateNote(note: Note) {
        note.run {
            when {
                title.isEmpty() -> throw IllegalArgumentException("Title of $note cannot be empty")
                content.isEmpty() -> throw IllegalArgumentException("Content of $note cannot be empty")
                else -> {//do nothing}
                }
            }
        }
    }
}