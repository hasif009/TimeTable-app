package com.hasif.timetableapp.departuresList.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.hasif.timetableapp.departuresList.ui.DepartureListViewModel

@Composable
fun DepartureListScreen(
    viewModel: DepartureListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    Box(modifier = modifier) {
        Text(text = "We will display the list here")
    }

}