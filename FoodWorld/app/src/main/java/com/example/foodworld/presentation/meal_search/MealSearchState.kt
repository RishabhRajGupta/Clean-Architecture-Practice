package com.example.foodworld.presentation.meal_search

import com.example.foodworld.domain.model.Meal

data class MealSearchState(
    val data:List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
){

}