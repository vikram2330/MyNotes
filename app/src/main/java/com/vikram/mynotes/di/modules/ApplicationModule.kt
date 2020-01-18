package com.vikram.mynotes.di.modules

import android.content.Context
import com.vikram.mynotes.MyNotesApp
import com.vikram.trendingrepos.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
open class ApplicationModule(private val app: MyNotesApp) {

    @Provides
    @ApplicationContext
    open fun provideAppContext(): Context = app.applicationContext

}