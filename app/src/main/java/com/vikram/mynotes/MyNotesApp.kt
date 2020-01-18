package com.vikram.mynotes

import android.app.Application
import com.vikram.mynotes.di.components.ApplicationComponent
import com.vikram.mynotes.di.components.DaggerApplicationComponent
import com.vikram.mynotes.di.modules.ApplicationModule


class MyNotesApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    companion object {
        private var INSTANCE: MyNotesApp? = null
        @JvmStatic
        fun get(): MyNotesApp = INSTANCE!!
    }
}