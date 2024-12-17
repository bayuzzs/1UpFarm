package com.example.oneupfarm.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun showSnackbar(snackBarHostState: SnackbarHostState, scope: CoroutineScope, message: String?) {
    scope.launch {
        snackBarHostState.showSnackbar(
            message ?: "",
            duration = SnackbarDuration.Short,
            withDismissAction = true
        )
    }
}