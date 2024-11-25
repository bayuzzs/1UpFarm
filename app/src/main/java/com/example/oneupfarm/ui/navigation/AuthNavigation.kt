package com.example.oneupfarm.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.oneupfarm.ui.screen.ChooseGenderScreen
import com.example.oneupfarm.ui.screen.LoginScreen
import com.example.oneupfarm.ui.screen.NewPasswordScreen
import com.example.oneupfarm.ui.screen.RegisterScreen
import com.example.oneupfarm.ui.screen.ResetPasswordScreen
import com.example.oneupfarm.ui.screen.WelcomeScreen

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    composable(Screen.Welcome.route) {
        WelcomeScreen(navController = navController)
    }
    composable(Screen.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(Screen.Register.route) {
        RegisterScreen(navController = navController)
    }
    composable(Screen.ChooseGender.route) {
        ChooseGenderScreen(navController = navController)
    }
    composable(Screen.ResetPassword.route) {
        ResetPasswordScreen(navController = navController)
    }
    composable(Screen.NewPassword.route){
        NewPasswordScreen(navController = navController)
    }
}