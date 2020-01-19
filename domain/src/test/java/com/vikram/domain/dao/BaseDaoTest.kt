package com.vikram.domain.dao

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.vikram.domain.roomdatabase.MNRoomDatabase
import org.junit.After
import org.junit.Before

abstract class BaseDaoTest {
    lateinit var mnDatabase: MNRoomDatabase

    @Before
    fun setUp() {
        val context = getApplicationContext() as Application
        mnDatabase =
            Room.inMemoryDatabaseBuilder(context, MNRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun tearDown(){
        mnDatabase.close()
    }
}