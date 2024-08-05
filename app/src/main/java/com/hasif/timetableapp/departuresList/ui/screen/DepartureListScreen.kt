package com.hasif.timetableapp.departuresList.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hasif.timetableapp.commonui.components.FullScreenLoading
import com.hasif.timetableapp.composeLifecycle.ActionHandlerDisposableEffect
import com.hasif.timetableapp.departuresList.ui.DepartureListAction
import com.hasif.timetableapp.departuresList.ui.DepartureListViewModel
import com.hasif.timetableapp.departuresList.ui.components.DepartureHeader
import com.hasif.timetableapp.departuresList.ui.components.DepartureItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartureListScreen(
    viewModel: DepartureListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    showSnackBarMessage: (String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    val isRefreshing = remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    val pullToRefreshState = rememberPullToRefreshState()

    ActionHandlerDisposableEffect(actionHandler = viewModel.action) { action ->
        when (action) {
            is DepartureListAction.ShowLoading -> {
                isRefreshing.value = true
            }

            is DepartureListAction.HideLoading -> {
                isRefreshing.value = false
            }

            is DepartureListAction.ShowErrorMessage -> {
                showSnackBarMessage(action.message)
            }
        }
    }

    val onRefresh: () -> Unit = {
        coroutineScope.launch {
            viewModel.fetchData()
        }
    }

    Surface(modifier = modifier) {
        FullScreenLoading(
            isLoading = isRefreshing.value,
            modifier = Modifier
        ) {
            Column {

                DepartureHeader(oncRefreshClicked = onRefresh)
                val uiState = viewModel.departureList.collectAsState().value

                if (uiState.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .nestedScroll(pullToRefreshState.nestedScrollConnection)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(all = 4.dp)
                                .testTag(DEPARTURE_LIST_TAG)
                        ) {
                            itemsIndexed(uiState) { position, item ->
                                DepartureItem(
                                    departure = item,
                                    modifier = Modifier.testTag("position=$position")
                                )

                                if (position < uiState.lastIndex)
                                    HorizontalDivider(
                                        color = Color.Gray,
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                            }
                        }
                    }
                }
            }
        }


    }

}

const val DEPARTURE_LIST_TAG = "departure_list"