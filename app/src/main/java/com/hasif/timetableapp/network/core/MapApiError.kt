package com.hasif.timetableapp.network.core

import retrofit2.HttpException
import javax.inject.Inject

class MapApiError @Inject constructor() {

    operator fun invoke(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> {
                /**
                 * This is the place where we should map various http expection and handle different scenarios
                 * and also parse the error body when server can send error messages to client side
                 * current just parsing it to simple message
                 */
                Throwable(
                    cause = throwable,
                    message = "Http exception, please try again later"
                )
            }

            else -> {
                throwable
            }
        }
    }
}