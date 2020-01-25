package com.vikram.mynotes

import android.os.Build
import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.mynotes.usecases.GetSavedNotesUseCase
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
class GetSavedNotesUseCaseTest {

    @Inject
    lateinit var getSavedNotesUseCase: GetSavedNotesUseCase
    @Inject
    lateinit var database: MNRoomDatabase

    @Before
    fun setup() {
        val testComponent = DaggerTestComponent.create()
        testComponent.inject(this)
    }

    @Test
    fun `test GetSavedNotesUseCase`() {
        val sizeOfItemsInserted = `add dummy notes in DataBase`()
        runBlocking {
            val notes = getSavedNotesUseCase.perform()
            Assert.assertTrue(notes.size == sizeOfItemsInserted)
        }
    }

    /**
     * This fun will add dummy data to database and then return
     * count of items inserted
     *
     * @return size of Items inserted
     */
    private fun `add dummy notes in DataBase`(): Int {
        return runBlocking {
            val notes = DataUtils.NoteData.notes
            database.noteDao().addMultipleNotes(notes)
            notes.size
        }
    }
}