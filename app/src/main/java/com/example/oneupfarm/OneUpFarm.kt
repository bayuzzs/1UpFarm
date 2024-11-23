package com.example.oneupfarm

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.navigation.getCurrentRoute
import com.example.oneupfarm.ui.screen.ChooseGenderScreen
import com.example.oneupfarm.ui.screen.LoginScreen
import com.example.oneupfarm.ui.screen.PlantMonitoringScreen
import com.example.oneupfarm.ui.screen.ProfileScreen
import com.example.oneupfarm.ui.screen.RegisterScreen
import com.example.oneupfarm.ui.screen.ResetPasswordScreen
import com.example.oneupfarm.ui.screen.TrackPlantScreen
import com.example.oneupfarm.ui.screen.WelcomeScreen

@Composable
fun OneUpFarm(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.getCurrentRoute()

    val shouldShowBottomBar: Boolean = navController.getCurrentRoute() !in listOf(
        Screen.Login.route,
        Screen.Register.route,
        Screen.Welcome.route,
        Screen.ChooseGender.route,
        Screen.ResetPassword.route,
    )

    val topBarContent: @Composable (() -> Unit)? = when (currentRoute) {
        else -> null
    }

    NavHost(
        navController = navController,
        startDestination = Screen.PlantMonitoring.route,
    ) {
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Calendar.route) { Text("Calendar Page") }
        composable(Screen.MarketPlace.route) { Text("MarketPlace Page") }
        composable(Screen.Register.route) { RegisterScreen(navController = navController) }
        composable(Screen.Login.route) { LoginScreen(navController = navController) }
        composable(Screen.Welcome.route) { WelcomeScreen(navController = navController) }
        composable(Screen.ChooseGender.route) { ChooseGenderScreen(navController = navController) }
        composable(Screen.ResetPassword.route) { ResetPasswordScreen(navController = navController) }
        composable(Screen.TrackPlant.route) { TrackPlantScreen(navController = navController) }
        composable(Screen.PlantMonitoring.route) {
            PlantMonitoringScreen(
                navController = navController,
                onClose = {})
        }
    }
}