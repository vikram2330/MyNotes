package com.vikram.domain.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
        @JvmStatic
        fun getInstance(context: Context, dbName: String = "notes_database"): MNRoomDatabase {
            return Room.databaseBuilder(context.applicationContext, MNRoomDatabase::class.java, dbName).build()
        }
    }

    abstract fun noteDao(): NoteDao
}