package com.hasif.timetableapp.network.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatchersModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = Dispatchers()
}