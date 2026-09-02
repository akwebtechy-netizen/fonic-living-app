package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.FurnitureRepository
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.screens.SavedInquiriesScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.WoodGuideScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.FurnitureViewModel

sealed class AppScreen(val route: String) {
    object Splash : AppScreen("splash")
    object Dashboard : AppScreen("dashboard")
    object Detail : AppScreen("detail/{productId}") {
        fun createRoute(productId: String) = "detail/$productId"
    }
    object SavedInquiries : AppScreen("saved_inquiries")
    object WoodGuide : AppScreen("wood_guide")
}

class MainActivity : ComponentActivity() {

    private val viewModel: FurnitureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                FonicLivingApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FonicLivingApp(viewModel: FurnitureViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(AppScreen.Splash.route) {
            SplashScreen(
                onEnterApp = {
                    navController.navigate(AppScreen.Dashboard.route) {
                        popUpTo(AppScreen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppScreen.Dashboard.route) {
            DashboardScreen(
                viewModel = viewModel,
                onProductClick = { product ->
                    viewModel.selectProduct(product)
                    navController.navigate(AppScreen.Detail.createRoute(product.id))
                },
                onBookmarksClick = {
                    navController.navigate(AppScreen.SavedInquiries.route)
                },
                onWoodGuideClick = {
                    navController.navigate(AppScreen.WoodGuide.route)
                }
            )
        }

        composable(
            route = AppScreen.Detail.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val product = FurnitureRepository.getProductById(productId)
                ?: FurnitureRepository.products.first()

            ProductDetailScreen(
                product = product,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onBookmarksClick = { navController.navigate(AppScreen.SavedInquiries.route) },
                onWoodGuideClick = { navController.navigate(AppScreen.WoodGuide.route) }
            )
        }

        composable(AppScreen.SavedInquiries.route) {
            SavedInquiriesScreen(
                viewModel = viewModel,
                onProductClick = { product ->
                    viewModel.selectProduct(product)
                    navController.navigate(AppScreen.Detail.createRoute(product.id))
                },
                onBackClick = { navController.popBackStack() },
                onWoodGuideClick = { navController.navigate(AppScreen.WoodGuide.route) }
            )
        }

        composable(AppScreen.WoodGuide.route) {
            WoodGuideScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

