package com.hasif.timetableapp.departuresList.data

import com.hasif.timetableapp.departuresList.database.dao.TimeTableDao
import com.hasif.timetableapp.departuresList.database.mappers.DepartureEntityMapper
import com.hasif.timetableapp.departuresList.mappers.TimeTableResponseMapper
import com.hasif.timetableapp.network.core.DispatcherProvider
import com.hasif.timetableapp.network.core.ExecuteApiRequest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeTableRepository @Inject constructor(
    private val fetchTimeTableApi: FetchTimeTableApi,
    private val timeTableResponseMapper: TimeTableResponseMapper,
    private val departureEntityMapper: DepartureEntityMapper,
    private val departureDao: TimeTableDao,
    private val dispatcherProvider: DispatcherProvider,
    private val executeApiRequest: ExecuteApiRequest,
) {

    suspend fun fetchTimeTableForDepartures(): Result<List<Departure>> {
        val result: Result<TimeTableResponse> = executeApiRequest.invoke {
            fetchTimeTableApi.fetchTimeTable()
        }
        return result.mapCatching { response ->
            timeTableResponseMapper.invoke(response.timeTable.departures)
        }.onSuccess { list ->
            withContext(dispatcherProvider.io) {
                departureDao.insertDepartureList(departureEntityMapper.invoke(list))
            }
        }
    }

}