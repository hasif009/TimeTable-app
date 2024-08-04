package com.hasif.timetableapp.departuresList.usecases

import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.data.TimeTableRepository
import javax.inject.Inject

class FetchDepartureListFromNetworkUseCase @Inject constructor(
    private val repository: TimeTableRepository,
) {

    suspend operator fun invoke(): Result<List<Departure>> =
        repository.fetchTimeTableForDepartures()
}