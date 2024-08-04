package com.hasif.timetableapp.departuresList.mappers

import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.data.Departures
import javax.inject.Inject

class TimeTableResponseMapper @Inject constructor(
    private val timeZoneMapper: TimeZoneMapper
) {

    suspend operator fun invoke(departures: List<Departures>): List<Departure> {
        return departures.map { item ->
            Departure(
                time = timeZoneMapper.invoke(item.dateTime),
                lineCode = item.lineCode,
                directiom = item.direction
            )
        }
    }
}