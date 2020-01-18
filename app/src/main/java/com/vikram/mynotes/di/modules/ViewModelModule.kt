package com.vikram.mynotes.di.modules

import androidx.lifecycle.ViewModelProvider
import com.vikram.mynotes.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}