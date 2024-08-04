package com.hasif.timetableapp.departuresList.data

data class Departure(
    val time: String,
    val lineCode: String,
    val direction: String,
    val rideId: Long,
)