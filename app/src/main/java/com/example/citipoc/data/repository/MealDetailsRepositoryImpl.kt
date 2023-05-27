package com.example.citipoc.data.repository
import com.example.citipoc.data.dto.MealDTO
import com.example.citipoc.data.remote.MealSearchAPI
import com.example.citipoc.data.dto.MealsResponseDTO
import com.example.citipoc.domain.repository.MealDetailsRepository
import com.example.citipoc.utils.Resource
import com.example.citipoc.utils.applyIoScheduler
import io.reactivex.Single

class MealDetailsRepositoryImpl(private val mealSearchAPI: MealSearchAPI) : MealDetailsRepository {

    override suspend fun getMealDetails(id: String): MealsResponseDTO {
        return mealSearchAPI.getMealDetails(id)
    }

    override fun getMealDetailsRx(id: String): Single<Resource<List<MealDTO>>> {
        return mealSearchAPI.getMealDetailsRx(id).applyIoScheduler().map {
Resource.Success(it.meals) as Resource<List<MealDTO>>
        }
    }
}