package com.hasif.timetableapp.departuresList.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeTableResponse(
    @SerialName("timetable")
    val timeTable: TimeTable
)

@Serializable
data class TimeTable(
    @SerialName("departures")
    val departures: List<Departures>
)

@Serializable
data class Departures(
    @SerialName("datetime")
    val dateTime: DateTime,
    @SerialName("direction")
    val direction: String,
    @SerialName("line_code")
    val lineCode: String,
)


@Serializable
data class DateTime(
    @SerialName("timestamp")
    val timestamp: Long,
    @SerialName("tz")
    val timeZone: String,
)