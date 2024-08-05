package com.hasif.timetableapp.network

import com.hasif.timetableapp.network.interceptors.BaseUrlInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkingModule {

    @Binds
    @Named(BASE_URL_INTERCEPTOR_KEY)
    abstract fun bindBaseUrlInterceptor(baseUrlInterceptor: BaseUrlInterceptor): Interceptor
}