package com.hasif.timetableapp.departuresList.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Departure")
data class DepartureEntity(
    @PrimaryKey val riderId: Long,
    val time: String,
    val lineCode: String,
    val direction: String,
)