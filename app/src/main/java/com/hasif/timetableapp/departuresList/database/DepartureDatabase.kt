package com.hasif.timetableapp.departuresList.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasif.timetableapp.departuresList.database.dao.TimeTableDao
import com.hasif.timetableapp.departuresList.database.entity.DepartureEntity

@Database(
    entities = [DepartureEntity::class],
    version = 1
)
abstract class TimeTableDatabase : RoomDatabase() {

    abstract fun departureDao(): TimeTableDao
}