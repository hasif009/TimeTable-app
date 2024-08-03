package com.hasif.timetableapp.departuresList.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.departureListNavigator(
    contentPadding: PaddingValues,
) {
    composable(DepartureListRoute) {
        DepartureListScreen(modifier = Modifier.padding(contentPadding))
    }
}

const val DepartureListRoute = "departure-list"