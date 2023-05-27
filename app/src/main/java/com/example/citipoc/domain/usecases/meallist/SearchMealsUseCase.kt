package com.example.citipoc.domain.usecases.meallist

import com.example.citipoc.domain.model.Meal
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.domain.usecases.BaseUseCase
import com.example.citipoc.utils.Resource
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface SearchMealsUseCase : BaseUseCase{
 fun getMealSearch(query:String): Flow<Resource<List<Meal>>>
 fun getMealSearchRx(s:String): Single<Resource<List<Meal>>>
}