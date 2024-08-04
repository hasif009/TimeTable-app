package com.hasif.timetableapp.departuresList.data

import retrofit2.http.GET

interface FetchTimeTableApi {

    @GET("mobile/v1/network/station/1/timetable.json")
    suspend fun fetchTimeTable(): TimeTableResponse
}