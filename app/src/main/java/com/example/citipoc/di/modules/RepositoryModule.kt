package com.example.citipoc.di.modules

import com.example.citipoc.data.remote.MealSearchAPI
import com.example.citipoc.data.repository.MealDetailsRepositoryImpl
import com.example.citipoc.data.repository.MealSearchRepositoryImpl
import com.example.citipoc.domain.repository.MealDetailsRepository
import com.example.citipoc.domain.repository.MealSearchRepository
import com.example.citipoc.domain.usecases.meallist.SearchMealsUseCase
import com.example.citipoc.domain.usecases.meallist.SearchMealsUseCaseImpl
import com.example.citipoc.domain.usecases.mealsdetails.GetMealDetailsUseCaseImpl
import com.example.citipoc.domain.usecases.mealsdetails.GetMealsDetailsUseCase
import dagger.Module
import dagger.Provides

@Module

object RepositoryModule {

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return MealSearchRepositoryImpl(mealSearchAPI)
    }
    @Provides
    fun provideMealDetails(searchMealSearchAPI: MealSearchAPI): MealDetailsRepository {
        return MealDetailsRepositoryImpl(searchMealSearchAPI)
    }
    @Provides
    fun provideMealDetailsUseCase(repository: MealDetailsRepository): GetMealsDetailsUseCase {
        return GetMealDetailsUseCaseImpl(repository)
    }

    @Provides
    fun provideMealSearchUseCase(repository: MealSearchRepository): SearchMealsUseCase {
        return SearchMealsUseCaseImpl(repository)
    }
}