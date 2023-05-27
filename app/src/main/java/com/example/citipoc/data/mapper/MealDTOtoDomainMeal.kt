package com.example.citipoc.data.mapper

import com.example.citipoc.data.dto.MealDTO
import com.example.citipoc.domain.model.Meal

fun MealDTO.toDomainMeal(): Meal {
    return Meal(
        id = this.idMeal,
        name = this.strMeal ?: "",
        image = this.strMealThumb ?: ""
    )
}