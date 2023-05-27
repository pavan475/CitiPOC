package com.example.citipoc.di.modules.activitymodule

import com.example.citipoc.di.modules.fragmentmodule.MainFragmentBuildersModule
import com.example.citipoc.di.modules.MainScope
import com.example.citipoc.di.modules.viewmodelmodule.ViewModelModule
import com.example.citipoc.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class, MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}