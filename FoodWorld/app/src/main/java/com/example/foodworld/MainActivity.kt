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
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodworld.presentation.meal_details.MealDetailsScreen
import com.example.foodworld.presentation.meal_search.MealSearchScreen
import com.example.foodworld.ui.theme.FoodWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodWorldTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController=navController,
                        startDestination = "meal_search_screen"
                    ){
                        // Screen 1: Search
                        composable("meal_search_screen"){
                            MealSearchScreen(
                                onMealClick = { mealId ->
                                    navController.navigate("meal_details_screen/$mealId")
                                }
                            )
                        }

                        // Screen 2: Details
                        composable(
                            route = "meal_details_screen/{mealId}",
                            arguments =  listOf(
                                navArgument("mealId"){ type = NavType.StringType }
                            )
                        ){ backStackEntry ->
                            MealDetailsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
