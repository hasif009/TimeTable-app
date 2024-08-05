package com.hasif.timetableapp.network.core

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineDispatchersModule::class]
)
class CoroutineTestDispatchersModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = TestDispatchers()
}