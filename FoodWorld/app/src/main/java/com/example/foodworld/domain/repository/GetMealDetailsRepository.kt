package com.example.foodworld.domain.repository

import com.example.foodworld.data.model.MealsDTO

interface GetMealDetailsRepository {

    suspend fun getMealDetails(id: String): MealsDTO

}