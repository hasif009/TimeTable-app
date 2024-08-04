package com.hasif.timetableapp.network.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher

    val computation: CoroutineDispatcher

    val io: CoroutineDispatcher
}