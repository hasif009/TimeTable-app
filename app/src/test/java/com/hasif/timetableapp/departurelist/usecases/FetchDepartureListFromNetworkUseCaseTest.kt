package com.hasif.timetableapp.departurelist.usecases

import com.google.common.truth.Truth
import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.data.TimeTableRepository
import com.hasif.timetableapp.departuresList.usecases.FetchDepartureListFromNetworkUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FetchDepartureListFromNetworkUseCaseTest {
    private val repository = mock<TimeTableRepository>()

    private val fetchDepartureListFromNetworkUseCase =
        FetchDepartureListFromNetworkUseCase(repository)

    @Test
    fun `verify result when repository contains data`() = runTest {
        whenever(repository.fetchTimeTableForDepartures()).thenReturn(
            Result.success(
                provideDepartures()
            )
        )
        val resul = fetchDepartureListFromNetworkUseCase.invoke()
        Truth.assertThat(resul).isEqualTo(Result.success(provideDepartures()))
    }

    private fun provideDepartures(): List<Departure> = listOf(
        Departure(time = "12.10", lineCode = "123", direction = "Oldenburg", rideId = 123L)
    )
}