package com.carlosalves.products

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.carlosalves.products.navigation.ProductsNavigation
import dagger.hilt.android.HiltAndroidApp
import com.carlosalves.products.ui.theme.ProductsTheme

@HiltAndroidApp
class ProductsApplication: Application()

@Composable
fun ProductsApp(navController: NavHostController) {
    ProductsTheme {
        ProductsNavigation(navController)
    }
}