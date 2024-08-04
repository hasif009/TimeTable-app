package com.hasif.timetableapp.departuresList.injection

import com.hasif.timetableapp.departuresList.data.FetchTimeTableApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class DepartureListModule {

    @Provides
    fun providesTimeTableService(
        retrofitBuilder: Retrofit.Builder,
    ): FetchTimeTableApi = retrofitBuilder.baseUrl(PLACEHOLDER_URL).build().create()

}

private const val PLACEHOLDER_URL = "http://localhost/"