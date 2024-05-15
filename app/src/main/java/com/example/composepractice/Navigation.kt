package com.example.composepractice

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composepractice.data.CartViewModel
import com.example.composepractice.data.model.findFruit
import com.example.composepractice.data.model.fruitList
import com.example.composepractice.ui.screen.Cart
import com.example.composepractice.ui.screen.FruitDetailScreen
import com.example.composepractice.ui.screen.FruitScreen
import com.example.composepractice.ui.screen.MasterScreen

sealed class Screen(val route: String) {
    object MasterScreen: Screen(route = "master_screen")
    object FruitScreen: Screen(route = "fruit_screen")
    object FruitDetailScreen: Screen(route = "fruit_detail_screen")
    object WeekBestSellersScreen: Screen(route = "week_best_sellers_screen")
    object CartScreen: Screen(route = "cart_screen")
}


@Composable
fun Navigation(viewModel: CartViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MasterScreen.route) {
        composable(Screen.MasterScreen.route) {
            MasterScreen(navController)
        }

        composable(Screen.FruitScreen.route) {
            FruitScreen(navController, fruitList)
        }

        composable(Screen.FruitDetailScreen.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("id")?.toInt()?.let { id->
                val fruit = findFruit(id)
                FruitDetailScreen(navController, fruit, viewModel)
            }
        }

        composable(Screen.CartScreen.route){
            Cart(navController, viewModel)
        }
    }
}

