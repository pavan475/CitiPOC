package com.example.citipoc.data.repository

import com.example.citipoc.data.dto.MealDTO
import com.example.citipoc.data.remote.MealSearchAPI
import com.example.citipoc.data.dto.MealsResponseDTO
import com.example.citipoc.domain.repository.MealSearchRepository
import com.example.citipoc.utils.Resource
import com.example.citipoc.utils.applyIoScheduler
import io.reactivex.Single

class MealSearchRepositoryImpl(private val mealSearchAPI: MealSearchAPI) : MealSearchRepository {

    override suspend fun getMealSearch(s: String): MealsResponseDTO {
        return mealSearchAPI.getSearchMealList(s)
    }

    override fun getMealSearchRx(s: String): Single<Resource<List<MealDTO>>> {
        return mealSearchAPI.getSearchMealListRx(s).applyIoScheduler().map {
            Resource.Success(it.meals) as Resource<List<MealDTO>>
        }
    }
}