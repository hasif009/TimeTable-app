package com.hasif.timetableapp.network

import com.hasif.timetableapp.BuildConfig
import com.hasif.timetableapp.network.interceptors.BaseUrlInterceptor
import com.hasif.timetableapp.network.interceptors.FlixHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val BASE_URL_INTERCEPTOR_KEY = "BaseUrlInterceptor"

@Module
@InstallIn(SingletonComponent::class)
class NetworkingCoreModule {

    @Provides
    @Singleton
    fun providesOkhttpClient(
        headerInterceptor: FlixHeaderInterceptor,
        @Named(BASE_URL_INTERCEPTOR_KEY) baseUrlInterceptor: Interceptor,
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(baseUrlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit.Builder {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit
            .Builder()
            .baseUrl("https://global.api-dev.flixbus.com")
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(contentType)
            )
    }

    companion object {
        private val contentType = "application/json".toMediaType()
    }

}