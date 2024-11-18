package com.example.oneupfarm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.navigation.addNewPlantNavigation
import com.example.oneupfarm.ui.navigation.authNavigation
import com.example.oneupfarm.ui.navigation.mainNavigation
import com.example.oneupfarm.ui.screen.CheckIconScreen

@Composable
fun OneUpFarm(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TrackPlant.route,
        modifier = modifier
    ) {
        // Auth Flow
        authNavigation(navController)

        // Main Flow
        mainNavigation(navController)

    }
}

