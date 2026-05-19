package com.example.foodworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodworld.presentation.meal_search.MealSearchScreen
import com.example.foodworld.ui.theme.FoodWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodWorldTheme { // Use your app's theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    MealSearchScreen(
                        onMealClick = { mealId ->
                            // Handle navigation to Detail Screen here
                        }
                    )
                }
            }
        }
    }
}
