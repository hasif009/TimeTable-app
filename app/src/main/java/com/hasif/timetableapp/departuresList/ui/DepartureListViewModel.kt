package com.hasif.timetableapp.departuresList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasif.timetableapp.composeLifecycle.ActionHandler
import com.hasif.timetableapp.departuresList.data.Departure
import com.hasif.timetableapp.departuresList.data.TimeTableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartureListViewModel @Inject constructor(
    private val timeTableRepository: TimeTableRepository,
    val action: ActionHandler<DepartureListAction>,
) : ViewModel() {

    private val _departureList: MutableStateFlow<List<Departure>> = MutableStateFlow(emptyList())

    val departureList: StateFlow<List<Departure>> = _departureList.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            action.update(DepartureListAction.ShowLoading)
            val result = timeTableRepository.fetchTimeTableForDepartures()
            action.update(DepartureListAction.HideLoading)
            result.onSuccess { departureList ->
                _departureList.value = departureList
            }.onFailure { error ->
                //do something here
                action.update(DepartureListAction.ShowErrorMessage(error.message.orEmpty()))
            }
        }
    }

}