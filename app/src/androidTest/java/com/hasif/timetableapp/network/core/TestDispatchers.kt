package com.hasif.timetableapp.network.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatchers @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val testDispatchers: TestDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = testDispatchers

    override val computation: CoroutineDispatcher
        get() = testDispatchers

    override val io: CoroutineDispatcher
        get() = testDispatchers
}