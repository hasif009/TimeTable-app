package com.hasif.timetableapp.departureList.test

import com.hasif.timetableapp.mock.LocalMockResponse

internal val responseWithDepartureListMocks = arrayOf(
    LocalMockResponse.Success(
        url = "/mobile/v1/network/station/1/timetable.json",
        responseBodyFilePath = "departurelist/fetch_success/list.json"
    )
)


internal val errorResponseWithDepartureListMocks = arrayOf(
    LocalMockResponse.Success(
        url = "/mobile/v1/network/station/1/timetable.json",
        responseCode = 400,
        responseBodyFilePath = ""
    )
)