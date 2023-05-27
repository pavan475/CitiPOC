package com.example.citipoc.domain.repository

import com.example.citipoc.data.dto.MealDTO
import com.example.citipoc.data.dto.MealsResponseDTO
import com.example.citipoc.utils.Resource
import io.reactivex.Single

interface MealSearchRepository : BaseRepository {
    suspend fun getMealSearch(s: String): MealsResponseDTO
    fun getMealSearchRx(s:String): Single<Resource<List<MealDTO>>>
}