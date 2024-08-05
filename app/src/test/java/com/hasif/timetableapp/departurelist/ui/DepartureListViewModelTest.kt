package com.hasif.timetableapp.departurelist.ui

import com.google.common.truth.Truth
import com.hasif.timetableapp.MainDispatcherRule
import com.hasif.timetableapp.composeLifecycle.ActionHandler
import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.ui.DepartureListAction
import com.hasif.timetableapp.departuresList.ui.DepartureListViewModel
import com.hasif.timetableapp.departuresList.usecases.FetchDepartureListFromNetworkUseCase
import com.hasif.timetableapp.departuresList.usecases.ObserveDeparturesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DepartureListViewModelTest {

    private val observeDeparturesUseCase = mock<ObserveDeparturesUseCase>()
    private val fetchDepartureListFromNetworkUseCase = mock<FetchDepartureListFromNetworkUseCase>()
    private val action = mock<ActionHandler<DepartureListAction>>()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    private val viewModel by lazy {
        DepartureListViewModel(
            observeDeparturesUseCase = observeDeparturesUseCase,
            fetchDepartureListFromNetworkUseCase = fetchDepartureListFromNetworkUseCase,
            action = action
        )
    }

    @Test
    fun `when viewmodel is invoked , result list should be empty if DB is empty`() = runTest {
        whenever(observeDeparturesUseCase.invoke()).doReturn(flowOf(emptyList()))
        val result = viewModel.departureList.firstOrNull()
        Truth.assertThat(result).isEmpty()
    }


    @Test
    fun `when viewmodel is invoked , result list should not be empty if DB has value`() = runTest {
        whenever(observeDeparturesUseCase.invoke()).doReturn(flowOf(provideDepartures()))
        val result = viewModel.departureList.firstOrNull()
        Truth.assertThat(result).isNotEmpty()
    }

    @Test
    fun `when fetchList is called , and api is successfull, store the list`() =
        runTest(testDispatcher) {
            whenever(observeDeparturesUseCase.invoke()).doReturn(flowOf(emptyList()))
            viewModel.fetchData()
            verify(action).update(DepartureListAction.ShowLoading)
            verify(fetchDepartureListFromNetworkUseCase).invoke()
            verify(action).update(DepartureListAction.HideLoading)
        }

    @Test
    fun `when fetchList is called , and api is not successfull, store the list`() =
        runTest(testDispatcher) {
            whenever(observeDeparturesUseCase.invoke()).doReturn(flowOf(emptyList()))
            whenever(fetchDepartureListFromNetworkUseCase.invoke()).doReturn(Result.failure(Exception("Some error message")))
            viewModel.fetchData()
            verify(action).update(DepartureListAction.ShowLoading)
            verify(fetchDepartureListFromNetworkUseCase).invoke()
            verify(action).update(DepartureListAction.HideLoading)
            verify(action).update(DepartureListAction.ShowErrorMessage("Some error message"))
        }

    private fun provideDepartures(): List<Departure> = listOf(
        Departure(time = "12.10", lineCode = "123", direction = "Oldenburg", rideId = 123L)
    )
}