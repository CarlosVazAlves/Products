package com.carlosalves.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.carlosalves.products.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class ProductsActivity: ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator
    val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var keepSplashScreenOn by mutableStateOf(true)

        lifecycleScope.launch {
            delay(1500)
            keepSplashScreenOn = false
        }
        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }

        setContent {
            val navController = rememberNavController()
            navigator.setNavController(navController)
            ProductsApp(navController)
        }
    }
}