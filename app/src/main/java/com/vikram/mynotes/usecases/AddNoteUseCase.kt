package com.vikram.mynotes.usecases

import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.repositories.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NotesRepository) : BaseUseCase<Unit, Note> {

    override suspend fun perform(params: Note) {
        repository.saveNote(params)
    }
}