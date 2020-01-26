package com.vikram.mynotes.usecases

import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.repositories.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val repository: NotesRepository) : BaseUseCase<Note?,Long> {

    override suspend fun perform(params: Long): Note? {
       return repository.getNoteById(params)
    }
}