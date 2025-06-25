package com.carlosalves.products.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlosalves.products.ui.screens.FormScreen
import com.carlosalves.products.ui.screens.MainScreen
import com.carlosalves.products.ui.screens.ProductDetailScreen
import com.carlosalves.products.ui.screens.ProductsListScreen

sealed class NavDestination(val route: String) {
    data object Main : NavDestination("main")
    data object ProductsList : NavDestination("products_list")
    data object ProductDetail : NavDestination("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    data object Form : NavDestination("form")
}

@Composable
fun ProductsNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Main.route
    ) {
        composable(route = NavDestination.Main.route) {
            MainScreen()
        }
        composable(route = NavDestination.ProductsList.route) {
            ProductsListScreen()
        }
        composable(route = NavDestination.ProductDetail.route, arguments = listOf(navArgument("productId") { nullable = false; type = NavType.IntType })) {
            ProductDetailScreen(it.arguments?.getInt("productId")!!)
        }
        composable(route = NavDestination.Form.route) {
            FormScreen()
        }
    }
}