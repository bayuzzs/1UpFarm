package com.example.oneupfarm

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.data.api.AuthApi
import com.example.oneupfarm.data.api.RetrofitClient
import com.example.oneupfarm.data.local.TokenManager
import com.example.oneupfarm.data.repository.AuthRepository
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.navigation.authNavigation
import com.example.oneupfarm.ui.navigation.mainNavigation
import com.example.oneupfarm.utils.showSnackbar
import com.example.oneupfarm.viewmodel.AuthViewModel
import com.example.oneupfarm.viewmodel.AuthViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OneUpFarm(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Setup retrofit
    val retrofit = remember { RetrofitClient.create(context) }

    // Ganti ke SecurePreferencesManager
    val securePreferencesManager = remember { TokenManager(context) }

    // Setup repository
    val authApi = remember { retrofit.create(AuthApi::class.java) }
    val authRepository = remember {
        AuthRepository(authApi, securePreferencesManager)
    }

    // Setup ViewModel
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )

    // Snackbar handler
    val message = authViewModel.message.collectAsState()
    val snackbarHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (message.value != null) {
        LaunchedEffect(message.value) {
            showSnackbar(snackbarHost, scope, message.value)
            authViewModel.clearMessage()
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHost) {
                Snackbar(
                    snackbarData = it,
                    containerColor = Color(0xFF7C19B9),
                    contentColor = Color.White
                )
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = modifier
        ) {
            // Auth Flow
            authNavigation(navController, authViewModel)
            // Main Flow
            mainNavigation(navController, authViewModel)
        }
    }
}

