package com.vikram.domain.dao

import android.os.Build
import com.google.common.truth.Truth
import com.vikram.domain.roomdatabase.dao.NoteDao
import com.vikram.domain.util.DataUtils
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NoteDaoTest : BaseDaoTest() {

    private val notesData = DataUtils.NoteData
    private val noteDao: NoteDao by lazy {
        mnDatabase.noteDao()
    }

    @Test
    fun getNotes(){
        runBlocking {
            noteDao.addMultipleNotes(notesData.notes)
            val notes = noteDao.getAllNotes()
            Truth.assertThat(notes.first().title == "test note 1")
        }
    }

}