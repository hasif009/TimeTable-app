package com.hasif.timetableapp.mock

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

class ForwardRequestToLocalMock @Inject constructor() {

    operator fun invoke(chain: Interceptor.Chain): Response {
        val endPoint = LocalMockManager.matchResponse(chain)
        if (endPoint == null) {
            return proceedWithFallbackResponse(chain)
        } else if (endPoint is LocalMockResponse.ConnectionError) {
            proceedWithIoException()
        }

        return proceedWithValidMock(chain)
    }

    private fun proceedWithFallbackResponse(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url.toString()
        Log.i(
            "ForwardRequestToLocalMock",
            "Local mock intercepted: Registered response was not found, Returning fallback response: $originalUrl"
        )

        return Response.Builder()
            .code(HTTP_OK)
            .protocol(Protocol.HTTP_2)
            .message("SUCCESS")
            .body("{}".toResponseBody("application/json".toMediaType()))
            .request(chain.request())
            .build()
    }

    private fun proceedWithIoException() {
        Log.i(
            "ForwardRequestToLocalMock",
            "Local mock intercepted: IOException is thrown as requested"
        )
        throw IOException("Local mock IO exception")
    }

    private fun proceedWithValidMock(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url.toString()

        Log.i("ForwardRequestToLocalMock", "Local mock intercepted, Forwarding Url $originalUrl")

        val newUrl = request.url
            .newBuilder()
            .scheme("http")
            .host(LocalMockManager.serverHost)
            .port(LocalMockManager.serverPort)
            .build()

        val newRequest = request
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}