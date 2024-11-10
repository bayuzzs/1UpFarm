package com.example.oneupfarm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavController.getCurrentRoute(): String? {
    val currentBackStackEntry = this.currentBackStackEntryAsState().value
    return currentBackStackEntry?.destination?.route
}
