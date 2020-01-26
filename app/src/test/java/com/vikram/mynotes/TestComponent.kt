package com.vikram.mynotes

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.mynotes.di.modules.RepositoryModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, TestDatabaseModule::class])
interface TestComponent {
fun inject(addNoteUseCaseTest: AddNoteUseCaseTest)
fun inject(getSavedNotesUseCaseTest: GetSavedNotesUseCaseTest)
fun inject(getNoteByIdUseCaseTest: GetNoteByIdUseCaseTest)
}

@Module
class TestDatabaseModule {
    @Singleton
    @Provides
    fun provideMNDatabase(): MNRoomDatabase {
        return Room.inMemoryDatabaseBuilder(getApplicationContext(), MNRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}