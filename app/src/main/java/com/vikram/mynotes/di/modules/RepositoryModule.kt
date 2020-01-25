package com.vikram.mynotes.di.modules

import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.mynotes.repositories.NotesRepository
import com.vikram.mynotes.repositories.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(database: MNRoomDatabase): NotesRepository {
        return NotesRepositoryImpl(database.noteDao())
    }
}