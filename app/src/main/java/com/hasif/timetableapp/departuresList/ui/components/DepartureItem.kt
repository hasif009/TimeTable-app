package com.hasif.timetableapp.departuresList.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hasif.timetableapp.departuresList.data.Departure

@Composable
fun DepartureItem(
    departure: Departure,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .weight(0.8F)
            .padding(all = 16.dp)) {
            Text(text = departure.directiom, maxLines = 2)
            Text(text = departure.lineCode, maxLines = 2)
        }
        Box(modifier = Modifier.padding(top = 16.dp, end = 8.dp)) {
            Text(text = departure.time)
        }
    }

}

@Preview
@Composable
fun DepartureItemPreview() {
    val departure = Departure(
        time = "12.30",
        directiom = "Essen",
        lineCode = "L027"
    )
    Surface {
        DepartureItem(departure = departure)
    }
}