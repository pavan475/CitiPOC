package com.example.citipoc.di.modules.viewmodelmodule

import androidx.lifecycle.ViewModel
import com.example.citipoc.di.modules.MainScope
import com.example.citipoc.presentation.ui.viewmodel.MealDetailsViewModel
import com.example.citipoc.presentation.ui.viewmodel.MealSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MealSearchViewModel::class)
    abstract fun mainViewModel(mainViewModel: MealSearchViewModel) : ViewModel

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MealDetailsViewModel::class)
    abstract fun mainViewModel2(mainViewModel2: MealDetailsViewModel) : ViewModel


}
