package com.hasif.timetableapp.departureList.screens

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasTestTag
import com.hasif.timetableapp.commonui.components.LOADING_TAG
import com.hasif.timetableapp.departuresList.ui.components.DIRECTION_TAG
import com.hasif.timetableapp.departuresList.ui.components.HEADER_TITLE_TAG
import com.hasif.timetableapp.departuresList.ui.screen.DEPARTURE_LIST_TAG
import com.hasif.timetableapp.waitUntilComposeNodeIsDisplayed
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

class DepartureListScreen(
    private val semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<DepartureListScreen>(semanticsProvider) {

    private val loading: KNode get() = child { hasTestTag(LOADING_TAG) }

    private val header: KNode get() = child { hasTestTag(HEADER_TITLE_TAG) }

    private val headerTitle: KNode
        get() = header.child {
            hasText("Departure List")
        }

    val departureList = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(DEPARTURE_LIST_TAG) },
        itemTypeBuilder = {
            itemType(::LazyListItemNode)
        },
        positionMatcher = { position -> hasTestTag("position=$position") }
    )


    fun expectIsLoadingDisplayed() {
        waitUntilComposeNodeIsDisplayed(loading, semanticsProvider)
    }

    fun verifyTitleText(title: String) {
        waitUntilComposeNodeIsDisplayed(header, semanticsProvider)
        headerTitle.assertTextContains(title)
    }

    fun departureListIsNotDisplayed() {
        departureList.assertIsNotDisplayed()
    }

}

class LazyListItemNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<LazyListItemNode>(semanticsNode, semanticsProvider) {

    val directionText: KNode
        get() = child {
            hasTestTag(DIRECTION_TAG)
        }
}

fun onDeliveryListScreen(
    composeTestRule: SemanticsNodeInteractionsProvider,
    function: DepartureListScreen.() -> Unit
) = ComposeScreen.onComposeScreen(composeTestRule, function)