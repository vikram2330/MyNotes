package com.vikram.domain.roomdatabase

import androidx.room.Database
import com.vikram.domain.roomdatabase.models.Note

@Database(
    entities =  [Note::class],
    version = MNRoomDatabase.VERSION
)
abstract class MNRoomDatabase {
    companion object {
        const val VERSION = 1
    }
}