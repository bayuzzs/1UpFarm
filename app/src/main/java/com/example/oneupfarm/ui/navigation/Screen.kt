package com.example.oneupfarm.ui.navigation

sealed class Screen(val route: String) {
    data object Profile : Screen("profile")
    data object Register : Screen("register")
    data object Login : Screen("login")

    //    contoh klo ada route parameter
//    data class Test(val userId: String) : Screen("profile/{userId}") {
//        fun createRoute(userId: String) = "profile/$userId"
//    }

}
