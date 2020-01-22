package com.vikram.mynotes.di.modules

import android.content.Context
import com.vikram.domain.roomdatabase.MNRoomDatabase
import com.vikram.trendingrepos.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMNDatabase(@ApplicationContext context: Context): MNRoomDatabase {
        return MNRoomDatabase.getInstance(context)
    }
}