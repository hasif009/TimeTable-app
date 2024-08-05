package com.hasif.timetableapp.departurelist.usecases

import com.google.common.truth.Truth
import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.database.dao.TimeTableDao
import com.hasif.timetableapp.departuresList.database.entity.DepartureEntity
import com.hasif.timetableapp.departuresList.database.mappers.DepartureUiMapper
import com.hasif.timetableapp.departuresList.usecases.ObserveDeparturesUseCase
import com.hasif.timetableapp.network.core.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ObserveDeparturesUseCaseTest {
    private val timeTableDao = mock<TimeTableDao>()
    private val departureUiMapper = DepartureUiMapper()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val dispatcherProvider = mock<DispatcherProvider> {
        on { io } doReturn testDispatcher
    }

    private val observeDeparturesUseCase = ObserveDeparturesUseCase(
        timeTableDao = timeTableDao,
        departureUiMapper = departureUiMapper,
        dispatcherProvider = dispatcherProvider
    )

    @Test
    fun `when db contains data, emit successfull UI data`() = runTest {
        whenever(timeTableDao.getDepartureList()).thenReturn(flowOf(provideDepartureEntities()))
        val result = observeDeparturesUseCase.invoke().firstOrNull()
        Truth.assertThat(result).isEqualTo(provideDepartures())
    }


    private fun provideDepartureEntities(): List<DepartureEntity> = listOf(
        DepartureEntity(time = "12.10", lineCode = "123", direction = "Oldenburg", riderId = 123L)
    )

    private fun provideDepartures(): List<Departure> = listOf(
        Departure(time = "12.10", lineCode = "123", direction = "Oldenburg", rideId = 123L)
    )
}