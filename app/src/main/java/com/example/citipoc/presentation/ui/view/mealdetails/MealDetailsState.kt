package com.example.citipoc.presentation.ui.view.mealdetails

import com.example.citipoc.domain.model.MealDetails


data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
)