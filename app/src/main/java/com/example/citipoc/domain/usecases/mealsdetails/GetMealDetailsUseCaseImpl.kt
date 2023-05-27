package com.example.citipoc.domain.usecases.mealsdetails

import com.example.citipoc.data.mapper.toDomainMealDetails
import com.example.citipoc.utils.Resource
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.domain.repository.MealDetailsRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCaseImpl @Inject constructor(private val repository: MealDetailsRepository):GetMealsDetailsUseCase {

    /*operator fun invoke(id: String): Flow<Resource<List<MealDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(id)
            val domainData =
                if (!data.meals.isNullOrEmpty()) data.meals.map { it -> it.toDomainMealDetails() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }*/


    override fun getMealDetails(id: String): Flow<Resource<List<MealDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(id)
            val domainData =
                if (!data.meals.isNullOrEmpty()) data.meals.map { it -> it.toDomainMealDetails() } else emptyList()

            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            if (e.code() == 400) {
                emit(Resource.Error(message = e.localizedMessage ?: "Bad Request"))
            } else if (e.code() == 500) {
                emit(Resource.Error(message = e.localizedMessage ?: "Internal Error"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong!!!"))
        }
    }

    override fun getMealsDetailsRx(id: String): Single<Resource<List<MealDetails>>> {
        return repository.getMealDetailsRx(id).map {
            it.data?.map { item->item.toDomainMealDetails()
            }?.let { it1 -> Resource.Success(it1) }
        }
    }
}