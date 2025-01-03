package com.example.oneupfarm.ui.navigation

sealed class Screen(val route: String) {
    data object Profile : Screen("profile")
    data object Register : Screen("register")
    data object Login : Screen("login")
    data object Welcome : Screen("welcome")
    data object ChooseGender : Screen("choose_gender")
    data object ResetPassword : Screen("reset_password")
    data object NewPassword : Screen("new_password")
    data object TrackPlant : Screen("track_plant")
    data object MarketPlace : Screen("marketplace")
    data object AddPlant : Screen("add_plant")
    data object PlantMonitoring: Screen("plant_monitoring")
    data object ToDo : Screen("to_do")
    data object Leaderboard : Screen("leaderboard")
    data object Settings : Screen("settings")
    data object Notification : Screen("notification")

    //    contoh klo ada route parameter
//    data class Test(val userId: String) : Screen("profile/{userId}") {
//        fun createRoute(userId: String) = "profile/$userId"
//    }
}
