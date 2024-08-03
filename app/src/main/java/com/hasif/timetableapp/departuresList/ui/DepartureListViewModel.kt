package com.hasif.timetableapp.departuresList.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepartureListViewModel @Inject constructor() : ViewModel() {


    fun init() {
        Log.d("DepartureListViewModel", "Init viewmodel is called")
    }

}