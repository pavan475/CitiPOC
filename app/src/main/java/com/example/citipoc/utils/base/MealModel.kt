package com.example.citipoc.utils.base

import com.example.citipoc.R
import com.example.citipoc.domain.model.Meal

class MealModel : BaseModel(R.layout.fragment_meal_search) {
    var list = mutableListOf<Meal>()
}