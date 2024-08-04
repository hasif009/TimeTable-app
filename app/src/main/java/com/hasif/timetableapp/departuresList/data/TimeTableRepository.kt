package com.hasif.timetableapp.departuresList.data

import com.hasif.timetableapp.departuresList.mappers.TimeTableResponseMapper
import com.hasif.timetableapp.network.core.ExecuteApiRequest
import javax.inject.Inject

class TimeTableRepository @Inject constructor(
    private val fetchTimeTableApi: FetchTimeTableApi,
    private val timeTableResponseMapper: TimeTableResponseMapper,
    private val executeApiRequest: ExecuteApiRequest,
) {

    suspend fun fetchTimeTableForDepartures(): Result<List<Departure>> {
        val result: Result<TimeTableResponse> = executeApiRequest.invoke {
            fetchTimeTableApi.fetchTimeTable()
        }
        return result.mapCatching { response ->
            timeTableResponseMapper.invoke(response.timeTable.departures)
        }
    }

}