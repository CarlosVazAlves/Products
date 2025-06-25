package com.carlosalves.products.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun navigateToProductsList() {
        navController.navigate(NavDestination.ProductsList.route)
    }

    fun navigateToProductDetail(productId: Int) {
        navController.navigate(NavDestination.ProductDetail.createRoute(productId))
    }

    fun navigateToForm() {
        navController.navigate(NavDestination.Form.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}