package com.hasif.timetableapp

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import io.github.kakaocup.compose.node.element.KNode

fun waitUntilComposeNodeIsDisplayed(
    node: KNode,
    testRule: SemanticsNodeInteractionsProvider,
    timeoutMillis: Long = 5_000L,
) {
    val matcher = node.delegate.interaction.nodeProvider.nodeMatcher.matcher
    with((testRule as ComposeContentTestRule)) {
        waitUntil(timeoutMillis) {
            onAllNodes(
                matcher = matcher,
                useUnmergedTree = true
            ).fetchSemanticsNodes().size == 1
        }
    }
}