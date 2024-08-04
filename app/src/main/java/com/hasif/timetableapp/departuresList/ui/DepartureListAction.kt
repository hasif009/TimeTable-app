package com.hasif.timetableapp.departuresList.ui

sealed class DepartureListAction {

    data object ShowLoading : DepartureListAction()

    data object HideLoading : DepartureListAction()

    data class ShowErrorMessage(val message: String) : DepartureListAction()
}