package com.hasif.timetableapp.mock

import org.junit.rules.TestWatcher
import org.junit.runner.Description

class LocalMockResponseTestRule : TestWatcher() {

    override fun starting(description: Description?) {}

    override fun finished(description: Description?) {
        LocalMockManager.reset()
    }

    fun givenLocalMocks(
        vararg responses: LocalMockResponse
    ) {
        LocalMockManager.enqueueResponses(responses = responses)
    }
}