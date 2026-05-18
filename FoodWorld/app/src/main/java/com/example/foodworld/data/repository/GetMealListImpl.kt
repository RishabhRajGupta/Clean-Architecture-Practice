package com.example.foodworld.data.repository

import com.example.foodworld.data.model.MealsDTO
import com.example.foodworld.data.remote.MealSearchAPI
import com.example.foodworld.domain.repository.MealSearchRepository

class GetMealListImpl(private val mealSearchAPI: MealSearchAPI): MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }
}