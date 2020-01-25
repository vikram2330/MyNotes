package com.vikram.mynotes

import android.os.Build
import androidx.test.runner.AndroidJUnitRunner
import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.usecases.AddNoteUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class AddNoteUseCaseTest {

    @Inject
    lateinit var addNoteUseCase: AddNoteUseCase
    @Inject
    lateinit var database : MNRoomDatabase

    @Before
    fun setup(){
        val testComponent = DaggerTestComponent.create()
        testComponent.inject(this)
    }

    @Test
    fun `test AddNoteUseCase`(){
        val notesApp =  Note("Hello","Test",System.currentTimeMillis())
       runBlocking {
           addNoteUseCase.perform(notesApp)
           val notes = database.noteDao().getAllNotes()
           Assert.assertTrue(notes.isNotEmpty())
       }
    }

}