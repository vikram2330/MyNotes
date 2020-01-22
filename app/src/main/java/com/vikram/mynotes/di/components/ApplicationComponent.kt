package com.vikram.mynotes.di.components

import com.vikram.mynotes.di.modules.ApplicationModule
import com.vikram.mynotes.di.modules.DatabaseModule
import com.vikram.mynotes.di.modules.RepositoryModule
import com.vikram.mynotes.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, ViewModelModule::class,DatabaseModule::class])
interface ApplicationComponent {
}