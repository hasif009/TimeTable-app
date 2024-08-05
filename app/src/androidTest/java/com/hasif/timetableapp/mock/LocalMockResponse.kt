package com.hasif.timetableapp.mock

sealed class LocalMockResponse(open val url: String) {

    data class Success(
        override val url: String,
        val responseCode: Int = HTTP_OK,
        val responseBodyFilePath: String
    ) : LocalMockResponse(url)

    data class HttpError(
        override val url: String,
        val responseCode: Int,
        val responseBodyFilePath: String,
    ) : LocalMockResponse(url)

    data class ConnectionError(
        override val url: String,
    ) : LocalMockResponse(url)
}

const val HTTP_OK = 200