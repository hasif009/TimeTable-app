package com.hasif.timetableapp.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class FlixHeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("X-Api-Authentication", TOKEN)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val TOKEN = "intervIEW_TOK3n"
    }
}