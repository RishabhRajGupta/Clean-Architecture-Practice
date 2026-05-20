package com.example.foodworld.presentation.meal_details

import com.example.foodworld.domain.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
)