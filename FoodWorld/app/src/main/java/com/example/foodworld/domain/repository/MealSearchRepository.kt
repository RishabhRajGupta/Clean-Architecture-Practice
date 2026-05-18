package com.example.foodworld.domain.repository

import com.example.foodworld.data.model.MealsDTO

interface MealSearchRepository {

    suspend fun getMealList(s: String): MealsDTO

}