package com.hasif.timetableapp.departureList.test

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ActivityScenario
import com.hasif.timetableapp.MainActivity
import com.hasif.timetableapp.departureList.screens.LazyListItemNode
import com.hasif.timetableapp.departureList.screens.onDeliveryListScreen
import com.hasif.timetableapp.departureList.screens.onMainScreen
import com.hasif.timetableapp.mock.LocalMockResponseTestRule
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
class DepartureListVerifyTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(),
) {

    private val composeTestRule = createEmptyComposeRule()

    private val localMockResponseTestRule = LocalMockResponseTestRule()

    @get:Rule
    var rule = RuleChain.outerRule(localMockResponseTestRule)
        .around(HiltAndroidRule(this))
        .around(composeTestRule)

    @Test
    fun `VerifyLoadingScreenComponentWhenAppIsLaunched`() = run {
        localMockResponseTestRule.givenLocalMocks(
            responses = responseWithDepartureListMocks
        )

        ActivityScenario.launch(MainActivity::class.java)

        step("Launch the Main Activity") {
            onMainScreen(composeTestRule) {
                expectMainScreenIsLoaded()
            }
        }

        step("launch Departure List screen and verify Loading Screen is displayed") {
            onDeliveryListScreen(composeTestRule) {
                expectIsLoadingDisplayed()
            }
        }
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `VerifyDepartureListItemsWhenApiIsSuccessfull`() = run {
        localMockResponseTestRule.givenLocalMocks(
            responses = responseWithDepartureListMocks
        )

        ActivityScenario.launch(MainActivity::class.java)

        step("Launch the Main Activity") {
            onMainScreen(composeTestRule) {
                expectMainScreenIsLoaded()
            }
        }

        step("launch Departure List Screen and Verify Departure List view is displayed") {
            onDeliveryListScreen(composeTestRule) {
                verifyTitleText("Departure List")
                departureList {
                    childAt<LazyListItemNode>(0) {
                        directionText.assertTextContains("Oldenburg")
                    }
                }
            }
        }

    }


    @Test
    fun `VerifyDepartureListItemsNotVisibleWhenApiIsFailure`() = run {
        localMockResponseTestRule.givenLocalMocks(
            responses = errorResponseWithDepartureListMocks
        )

        ActivityScenario.launch(MainActivity::class.java)

        step("Launch the Main Activity") {
            onMainScreen(composeTestRule) {
                expectMainScreenIsLoaded()
            }
        }

        step("launch Departure List Screen and Verify Departure List view is not displayed") {
            onDeliveryListScreen(composeTestRule) {
                verifyTitleText("Departure List")
                departureListIsNotDisplayed()
            }
        }

    }

}