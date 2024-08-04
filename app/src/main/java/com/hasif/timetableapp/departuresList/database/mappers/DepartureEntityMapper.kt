package com.hasif.timetableapp.departuresList.database.mappers

import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.database.entity.DepartureEntity
import javax.inject.Inject

class DepartureEntityMapper @Inject constructor() {

    operator fun invoke(reponseList: List<Departure>): List<DepartureEntity> {
        return reponseList.map { item ->
            DepartureEntity(
                riderId = item.rideId,
                lineCode = item.lineCode,
                direction = item.direction,
                time = item.time
            )
        }
    }
}