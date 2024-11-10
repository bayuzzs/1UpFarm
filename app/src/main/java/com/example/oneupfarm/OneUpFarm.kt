package com.example.oneupfarm

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.ui.component.OUFBottomBar
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.navigation.getCurrentRoute
import com.example.oneupfarm.ui.screen.LoginScreen
import com.example.oneupfarm.ui.screen.TrackPlantScreen

@Composable
fun OneUpFarm(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.getCurrentRoute()

    val shouldShowBottomBar: Boolean = navController.getCurrentRoute() !in listOf(
//        Screen.Login.route,
        Screen.Register.route
    )

    val topBarContent: @Composable (() -> Unit)? = when (currentRoute) {
        else -> null
    }

    Scaffold(
        topBar = { topBarContent?.invoke() },
        bottomBar = {
            if (shouldShowBottomBar) {
                OUFBottomBar(navController = navController,
                    modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues())
                    )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) { TrackPlantScreen() }
        }
    }

}