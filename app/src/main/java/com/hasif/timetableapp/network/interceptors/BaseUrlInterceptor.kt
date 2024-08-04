package com.hasif.timetableapp.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BaseUrlInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var baseUrl = request.url
        if (baseUrl.toString().contains(PLACE_HOLDER_URL)) {
            baseUrl = request.url.newBuilder().scheme("https").host(HOST).build()
        }
        return chain.proceed(request.newBuilder().url(baseUrl).build())
    }

    companion object {
        const val PLACE_HOLDER_URL = "http://localhost/"
        private const val HOST = "global.api-dev.flixbus.com"
    }
}