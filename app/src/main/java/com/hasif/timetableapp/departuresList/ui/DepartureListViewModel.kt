package com.hasif.timetableapp.departuresList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasif.timetableapp.composeLifecycle.ActionHandler
import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.data.TimeTableRepository
import com.hasif.timetableapp.departuresList.usecases.FetchDepartureListFromNetworkUseCase
import com.hasif.timetableapp.departuresList.usecases.ObserveDeparturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartureListViewModel @Inject constructor(
    private val observeDeparturesUseCase: ObserveDeparturesUseCase,
    private val fetchDepartureListFromNetworkUseCase: FetchDepartureListFromNetworkUseCase,
    val action: ActionHandler<DepartureListAction>,
) : ViewModel() {

    private val _departureList: MutableStateFlow<List<Departure>> = MutableStateFlow(emptyList())

    val departureList: StateFlow<List<Departure>> = _departureList.asStateFlow()

    init {
        viewModelScope.launch {
            observeDeparturesUseCase.invoke().collect {
                _departureList.value = it
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            action.update(DepartureListAction.ShowLoading)
            val result = fetchDepartureListFromNetworkUseCase.invoke()
            action.update(DepartureListAction.HideLoading)
            result.onFailure { error ->
                action.update(DepartureListAction.ShowErrorMessage(error.message.orEmpty()))
            }
        }
    }

}