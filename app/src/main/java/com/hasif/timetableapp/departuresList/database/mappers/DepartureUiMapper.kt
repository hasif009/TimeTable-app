package com.hasif.timetableapp.departuresList.database.mappers

import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.database.entity.DepartureEntity
import javax.inject.Inject

class DepartureUiMapper @Inject constructor() {

    operator fun invoke(list: List<DepartureEntity>): List<Departure> {
        return list.map { dbItem ->
            Departure(
                time = dbItem.time,
                direction = dbItem.direction,
                lineCode = dbItem.lineCode,
                rideId = dbItem.riderId
            )
        }
    }
}