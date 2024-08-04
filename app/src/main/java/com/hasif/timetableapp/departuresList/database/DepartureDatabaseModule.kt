package com.hasif.timetableapp.departuresList.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DepartureDatabaseModule {


    @Provides
    @Singleton
    fun providesDatabase(application: Application): TimeTableDatabase {
        val builder =
            Room.databaseBuilder(application, TimeTableDatabase::class.java, "time_table.db")
                .fallbackToDestructiveMigration()

        return builder.build()
    }

    @Provides
    fun providesDepartureDao(db: TimeTableDatabase) = db.departureDao()
}