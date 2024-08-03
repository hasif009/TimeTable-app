package com.hasif.timetableapp.composeLifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ActionHandler<S>(private val state: MutableLiveData<Event<S>>) {

    @Inject
    constructor() : this(MutableLiveData())

    fun update(value: S) {
        state.value = Event(value)
    }

    internal fun observe(lifecycleOwner: LifecycleOwner, observer: EventObserver<S>) {
        state.observe(lifecycleOwner, observer)
    }

    internal fun removeObserver(observer: EventObserver<S>) {
        state.removeObserver(observer)
    }
}