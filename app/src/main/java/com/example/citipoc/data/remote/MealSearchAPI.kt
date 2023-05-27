package com.example.citipoc.data.remote

import com.example.citipoc.data.dto.MealsResponseDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchAPI {
    @GET("api/json/v1/1/search.php")
    suspend fun getSearchMealList(
        @Query("s") query: String
    ): MealsResponseDTO

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetails(
        @Query("i") i: String
    ): MealsResponseDTO

    @GET("api/json/v1/1/search.php")
     fun getSearchMealListRx(
        @Query("s") query: String
    ): Single<MealsResponseDTO>

    @GET("api/json/v1/1/lookup.php")
     fun getMealDetailsRx(
        @Query("i") i: String
    ): Single<MealsResponseDTO>
}