package com.hasif.timetableapp.departureList.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.hasif.timetableapp.MAIN_HOST_TAG
import com.hasif.timetableapp.commonui.components.LOADING_TAG
import com.hasif.timetableapp.waitUntilComposeNodeIsDisplayed
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainScreen(
    private val semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<MainScreen>(semanticsProvider) {

    private val mainScreen: KNode get() = child { hasTestTag(MAIN_HOST_TAG) }


    fun expectMainScreenIsLoaded() {
        waitUntilComposeNodeIsDisplayed(mainScreen, semanticsProvider)
    }

}

fun onMainScreen(
    composeTestRule: SemanticsNodeInteractionsProvider,
    function: MainScreen.() -> Unit
) = ComposeScreen.onComposeScreen(composeTestRule, function)