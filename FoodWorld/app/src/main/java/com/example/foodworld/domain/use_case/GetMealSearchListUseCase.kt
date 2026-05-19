package com.example.foodworld.domain.use_case

import com.example.foodworld.common.Resource
import com.example.foodworld.data.model.toDomainMeal
import com.example.foodworld.domain.model.Meal
import com.example.foodworld.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealList(s)

            val list = if(response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map { it.toDomainMeal() }

            emit(Resource.Success(data = list))
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