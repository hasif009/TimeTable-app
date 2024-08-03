package com.hasif.timetableapp.composeLifecycle

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {

    var consumed = false
        private set //Allow external read but not write

    /**
     * consumes the content if it is not been consumed yet
     * @return The uncomsumed content or 'null' if it was consumed already
     */
    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }
}

class EventObserver<T>(private val onEventUnConsumedContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(value: Event<T>) {
        value.consume()?.run(onEventUnConsumedContent)
    }
}