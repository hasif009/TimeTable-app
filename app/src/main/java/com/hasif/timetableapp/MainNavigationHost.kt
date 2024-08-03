package com.hasif.timetableapp

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hasif.timetableapp.departuresList.ui.screen.DepartureListRoute
import com.hasif.timetableapp.departuresList.ui.screen.departureListNavigator

@Composable
internal fun MainNavigationHost(
    navController: NavHostController,
) {

    Scaffold { contentPadding ->
        NavHost(navController = navController, startDestination = DepartureListRoute) {
            departureListNavigator(contentPadding)
        }

    }
}