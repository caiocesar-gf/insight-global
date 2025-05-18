package com.project.sakeshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.sakeshop.presentation.screens.SakeShopDetailScreen
import com.project.sakeshop.presentation.screens.SakeShopListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SakeShopListScreen.route
    ) {
        composable(route = Screen.SakeShopListScreen.route) {
            SakeShopListScreen(navController = navController)
        }

        composable(
            route = Screen.SakeShopDetailScreen.route + "/{shopId}",
            arguments = listOf(
                navArgument("shopId") {
                    type = NavType.IntType
                }
            )
        ) {
            SakeShopDetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object SakeShopListScreen : Screen("sake_shop_list")
    object SakeShopDetailScreen : Screen("shop_detail")
}