package com.example.citipoc.domain.usecases.mealsdetails

import com.example.citipoc.utils.Resource
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.domain.usecases.BaseUseCase
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface GetMealsDetailsUseCase : BaseUseCase {
    fun getMealDetails(id:String) : Flow<Resource<List<MealDetails>>>
    fun getMealsDetailsRx(id:String) : Single<Resource<List<MealDetails>>>
}