package com.example.citipoc.di.modules.fragmentmodule

import com.example.citipoc.presentation.ui.view.mealdetails.MealDetailsFragment
import com.example.citipoc.presentation.ui.view.mealsearch.MealSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMealFragment(): MealSearchFragment

    @ContributesAndroidInjector
    abstract fun contributeMealDetails() : MealDetailsFragment

}