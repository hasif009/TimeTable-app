package com.hasif.timetableapp.mock

import com.hasif.timetableapp.network.BASE_URL_INTERCEPTOR_KEY
import com.hasif.timetableapp.network.NetworkingModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.Interceptor
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkingModule::class]
)
abstract class TestNetworkingCoreModule {

    @Binds
    @Named(BASE_URL_INTERCEPTOR_KEY)
    abstract fun bindsBaseUrlInterceptor(baseUrlInterceptor: BaseUrlInterceptor): Interceptor
}