package com.hasif.timetableapp.composeLifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun <S> ActionHandlerDisposableEffect(
    actionHandler: ActionHandler<S>,
    observer: (S) -> Unit
) {
    val currentLifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(actionHandler, currentLifeCycleOwner) {
        val eventObserver = EventObserver(observer)
        actionHandler.observe(currentLifeCycleOwner, eventObserver)
        onDispose {
            actionHandler.removeObserver(eventObserver)
        }
    }
}