package com.vikram.domain.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vikram.domain.roomdatabase.dao.NoteDao
import com.vikram.domain.roomdatabase.models.Note

@Database(
    entities = [Note::class],
    version = MNRoomDatabase.VERSION
)
abstract class MNRoomDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun noteDao(): NoteDao
}