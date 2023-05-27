package com.example.citipoc.di.modules.viewmodelmodule

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds   //binds do exactly what provides does more efficiently
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}