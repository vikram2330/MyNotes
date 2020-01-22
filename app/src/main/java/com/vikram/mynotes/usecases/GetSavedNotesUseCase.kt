package com.vikram.mynotes.usecases

import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.repositories.NotesRepository
import javax.inject.Inject

class GetSavedNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) :
    BaseUseCase<List<Note>, Unit> {
    override suspend fun perform(): List<Note> {
        return notesRepository.getAllNotes()
    }
}