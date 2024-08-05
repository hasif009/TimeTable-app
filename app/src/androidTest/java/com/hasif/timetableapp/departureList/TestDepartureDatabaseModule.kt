package com.hasif.timetableapp.departureList

import android.app.Application
import androidx.room.Room
import com.hasif.timetableapp.departuresList.database.DepartureDatabaseModule
import com.hasif.timetableapp.departuresList.database.TimeTableDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DepartureDatabaseModule::class]
)
class TestDepartureDatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): TimeTableDatabase {
        val builder =
            Room.inMemoryDatabaseBuilder(application, TimeTableDatabase::class.java)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()

        return builder.build()
    }

    @Provides
    fun providesDepartureDao(db: TimeTableDatabase) = db.departureDao()
}