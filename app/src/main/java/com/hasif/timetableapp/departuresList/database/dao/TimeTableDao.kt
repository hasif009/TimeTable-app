package com.hasif.timetableapp.departuresList.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasif.timetableapp.departuresList.database.entity.DepartureEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TimeTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDepartureList(departureList: List<DepartureEntity>)

    @Query("SELECT * From departure")
    abstract fun getDepartureList(): Flow<List<DepartureEntity>>

}