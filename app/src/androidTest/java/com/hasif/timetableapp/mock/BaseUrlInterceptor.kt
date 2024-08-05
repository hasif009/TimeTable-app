package com.hasif.timetableapp.mock

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BaseUrlInterceptor @Inject constructor(
    private val forwardRequestToLocalMock: ForwardRequestToLocalMock
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return forwardRequestToLocalMock(chain)
    }
}