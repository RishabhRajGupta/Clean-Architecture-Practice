package com.example.foodworld.presentation.meal_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodworld.common.Resource
import com.example.foodworld.domain.use_case.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MealDetailsState())
    val state: StateFlow<MealDetailsState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("mealId")?.let { mealId ->
            getMealDetails(mealId)
        }
    }

    private fun getMealDetails(id: String) {
        getMealDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MealDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MealDetailsState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = MealDetailsState(error = result.message ?: "Unknown Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}