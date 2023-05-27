package com.example.citipoc.presentation.ui.view.mealsearch

import com.example.citipoc.domain.model.Meal


data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)