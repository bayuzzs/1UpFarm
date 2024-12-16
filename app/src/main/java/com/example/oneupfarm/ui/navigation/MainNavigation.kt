package com.example.oneupfarm.ui.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.oneupfarm.ui.screen.AddPlantScreen
import com.example.oneupfarm.ui.screen.PlantMonitoringScreen
import com.example.oneupfarm.ui.screen.ProfileScreen
import com.example.oneupfarm.ui.screen.SettingsScreen
import com.example.oneupfarm.ui.screen.TrackPlantScreen
import com.example.oneupfarm.viewmodel.AuthViewModel

fun NavGraphBuilder.mainNavigation(navController: NavHostController, authViewModel: AuthViewModel) {
    composable(Screen.Profile.route) {
        ProfileScreen(navController, authViewModel)
    }
    composable(Screen.Calendar.route) {
        Text("Calendar Page")
    }
    composable(Screen.MarketPlace.route) {
        Text("MarketPlace Page")
    }
    composable(Screen.TrackPlant.route) {
        TrackPlantScreen(navController = navController)
    }
    composable(Screen.Settings.route) {
        SettingsScreen(navController = navController, authViewModel)
    }

    composable(
        route = Screen.AddPlant.route,
        enterTransition = { slideInTransition() },
        exitTransition = { slideOutTransition() },
        popEnterTransition = { slideInTransitionPop() },
        popExitTransition = { slideOutTransitionPop() }
    ) {
        AddPlantScreen(navController, onClose = { navController.popBackStack() })
    }

    composable(Screen.PlantMonitoring.route) {
        PlantMonitoringScreen(
            navController = navController,
            onClose = {})
    }
}