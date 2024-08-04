package com.hasif.timetableapp.network.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExecuteApiRequest @Inject constructor(
    private val mapApiError: MapApiError,
    private val dispatchers: DispatcherProvider,
) {

    suspend operator fun <T> invoke(call: suspend () -> T): Result<T> {
        return withContext(dispatchers.io) {
            try {
                Result.success(call())
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (exception: Exception) {
                //Log error to analytics for enpoints
                Result.failure(mapApiError(exception))
            }
        }
    }
}