package com.example.citipoc.domain.repository

import com.example.citipoc.data.dto.MealDTO
import com.example.citipoc.data.dto.MealsResponseDTO
import com.example.citipoc.utils.Resource
import io.reactivex.Single

interface MealDetailsRepository : BaseRepository{
    suspend fun getMealDetails(id:String): MealsResponseDTO
    fun getMealDetailsRx(id:String):Single<Resource<List<MealDTO>>>
}