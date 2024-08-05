package com.hasif.timetableapp.mock

import okhttp3.Interceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

object LocalMockManager {

    private lateinit var mockServer: MockWebServer
    private val localMockResponse: BlockingDeque<LocalMockResponse> = LinkedBlockingDeque()

    val serverHost: String
        get() = mockServer.hostName

    val serverPort: Int
        get() = mockServer.port

    private var shouldHandleRequests = false

    fun enqueueResponses(
        vararg responses: LocalMockResponse
    ) {
        shouldHandleRequests = true
        reset()
        mockServer = MockWebServer()
        localMockResponse.addAll(responses)
        dispatch()
        mockServer.start()

    }

    private fun dispatch() {
        localMockResponse.forEach {
            when (it) {
                is LocalMockResponse.Success -> enqueueResponses(
                    responseCode = it.responseCode,
                    responseBodyFilePath = it.responseBodyFilePath
                )

                is LocalMockResponse.HttpError -> enqueueResponses(
                    responseCode = it.responseCode,
                    responseBodyFilePath = it.responseBodyFilePath
                )

                is LocalMockResponse.ConnectionError -> {
                    //Server is not reachable , no need to queue the response
                }
            }
        }
    }

    private fun enqueueResponses(responseCode: Int, responseBodyFilePath: String) {
        val responseBody = getContentFromFile(responseBodyFilePath)
        mockServer.enqueue(
            MockResponse().apply {
                setResponseCode(responseCode)
                setBody(responseBody)
            }
        )
    }

    fun reset() {
        localMockResponse.clear()
        if (::mockServer.isInitialized) {
            mockServer.shutdown()
        }
    }

    fun shouldHandleRequest(): Boolean {
        return shouldHandleRequests
    }

    fun matchResponse(chain: Interceptor.Chain): LocalMockResponse? {
        val requestUrl = chain.request().url.encodedPath

        return localMockResponse.peek()?.takeIf { it.url == requestUrl }
            ?.let { localMockResponse.poll() }
    }
}