package com.example.foodworld.data.repository

import com.example.foodworld.data.model.MealsDTO
import com.example.foodworld.data.remote.MealSearchAPI
import com.example.foodworld.domain.repository.GetMealDetailsRepository

class GetMealDetailsImpl(private val mealSearchAPI: MealSearchAPI): GetMealDetailsRepository{

    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }

}

