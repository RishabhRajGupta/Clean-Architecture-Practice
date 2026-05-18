package com.example.foodworld.domain.use_case

import com.example.foodworld.common.Resource
import com.example.foodworld.data.model.toDomainMeal
import com.example.foodworld.data.model.toDomainMealDetails
import com.example.foodworld.domain.model.Meal
import com.example.foodworld.domain.model.MealDetails
import com.example.foodworld.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: GetMealDetailsRepository) {
    operator fun invoke(id: String): Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealDetails(id).meals[0].toDomainMealDetails()

            emit(Resource.Success(data = response))
        }
        catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        }
        catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check your internet connection"))
        }
        catch (e: Exception){

        }
    }
}