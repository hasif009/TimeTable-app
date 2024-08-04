package com.hasif.timetableapp.departuresList.usecases

import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.database.dao.TimeTableDao
import com.hasif.timetableapp.departuresList.database.mappers.DepartureUiMapper
import com.hasif.timetableapp.network.core.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ObserveDeparturesUseCase @Inject constructor(
    private val timeTableDao: TimeTableDao,
    private val departureUiMapper: DepartureUiMapper,
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend operator fun invoke(): Flow<List<Departure>> = withContext(dispatcherProvider.io) {
        timeTableDao.getDepartureList().map {
            departureUiMapper.invoke(it)
        }
    }
}