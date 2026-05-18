package com.example.foodworld.hilt

import com.example.foodworld.common.Constants
import com.example.foodworld.data.remote.MealSearchAPI
import com.example.foodworld.data.repository.GetMealDetailsImpl
import com.example.foodworld.data.repository.GetMealListImpl
import com.example.foodworld.domain.repository.GetMealDetailsRepository
import com.example.foodworld.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchAPI{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSerachRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return GetMealListImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI): GetMealDetailsRepository {
        return GetMealDetailsImpl(mealSearchAPI)
    }
}