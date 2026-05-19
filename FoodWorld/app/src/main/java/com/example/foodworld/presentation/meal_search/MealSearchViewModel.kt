package com.example.foodworld.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodworld.common.Resource
import com.example.foodworld.domain.use_case.GetMealDetailsUseCase
import com.example.foodworld.domain.use_case.GetMealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCase: GetMealSearchListUseCase): ViewModel(){

    private val _state = MutableStateFlow<MealSearchState>(MealSearchState())
    val state: StateFlow<MealSearchState> = _state.asStateFlow()

    fun searchMealList(query: String){
        getMealSearchListUseCase(query).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = MealSearchState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = MealSearchState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Success -> {
                    _state.value = MealSearchState(data = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}
