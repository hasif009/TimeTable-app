package com.hasif.timetableapp

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hasif.timetableapp.departuresList.ui.screen.DepartureListRoute
import com.hasif.timetableapp.departuresList.ui.screen.departureListNavigator
import kotlinx.coroutines.launch

@Composable
internal fun MainNavigationHost(
    navController: NavHostController,
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val showSnackbarMessage: (String) -> Unit = { message ->
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState, modifier = Modifier)
        }
    ) { contentPadding ->
        NavHost(navController = navController, startDestination = DepartureListRoute) {
            departureListNavigator(contentPadding, showSnackbarMessage)
        }

    }
}