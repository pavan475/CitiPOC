package com.example.citipoc.domain.usecases.meallist

import com.example.citipoc.data.mapper.toDomainMeal
import com.example.citipoc.data.mapper.toDomainMealDetails
import com.example.citipoc.utils.Resource
import com.example.citipoc.domain.model.Meal
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.domain.repository.MealSearchRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMealsUseCaseImpl @Inject constructor(private val repository: MealSearchRepository):SearchMealsUseCase {

    //coroutine function
    override fun getMealSearch(q:String) : Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealSearch(q)
            //maps the response domain data model
            val domainData =
                if (data.meals != null) data.meals.map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }

    //Rx kotlin
    override fun getMealSearchRx(s: String): Single<Resource<List<Meal>>> {
        return repository.getMealSearchRx(s).map {
            it.data?.map { item->item.toDomainMeal()
            }?.let { it1 -> Resource.Success(it1) }
        }
    }
}