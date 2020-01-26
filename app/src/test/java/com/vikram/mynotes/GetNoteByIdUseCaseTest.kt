package com.vikram.mynotes

import android.os.Build
import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.mynotes.usecases.GetNoteByIdUseCase
import com.vikram.mynotes.util.DataUtils
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
class GetNoteByIdUseCaseTest {

    @Inject
    lateinit var getNoteByIdUseCase: GetNoteByIdUseCase
    @Inject
    lateinit var database: MNRoomDatabase
    lateinit var noteIds: List<Long>

    @Before
    fun setup() {
        val testComponent = DaggerTestComponent.create()
        testComponent.inject(this)
    }

    @Test
    fun `test GetSavedNotesUseCase with valid id`() {
        `add dummy notes in database`()
        runBlocking {
            val note = getNoteByIdUseCase.perform(noteIds.first())
            Assert.assertEquals(note?.id, noteIds.first())
        }
    }

    @Test
    fun `test GetSavedNotesUseCase with incorrect id`() {
        `add dummy notes in database`()
        runBlocking {
            val note = getNoteByIdUseCase.perform(0)
            Assert.assertNull(note)
        }
    }

    private fun `add dummy notes in database`() {
        runBlocking {
            val notes = DataUtils.NoteData.notes
            noteIds = database.noteDao().addMultipleNotes(notes)
        }
    }
}