package com.hasif.timetableapp.departuresList.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DepartureHeader(
    modifier: Modifier = Modifier,
    oncRefreshClicked: () -> Unit

) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color.Green)
    ) {

        Text(
            text = "Departure List",
            modifier = Modifier
                .weight(0.9F)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )

        IconButton(onClick = oncRefreshClicked) {
            Icon(Icons.Filled.Refresh, "Trigger Refresh")
        }
    }
}