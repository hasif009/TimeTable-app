package com.hasif.timetableapp.commonui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hasif.timetableapp.R

@Composable
fun FullScreenLoading(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        content()
        if (isLoading) {
            Scrim()
        }
        LoadingView(show = isLoading)

        BackHandler(enabled = isLoading) {
            // do nothing when non cancellable loading is in loading state
        }
    }
}

@Composable
private fun Scrim(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = SolidColor(Color(android.graphics.Color.parseColor("#171717"))),
                alpha = 0.72F
            )
    )
}

@Composable
private fun BoxScope.LoadingView(show: Boolean) {
    if (show) {
        Column(
            modifier = Modifier
                .testTag(LOADING_TAG)
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(android.graphics.Color.parseColor("#ffffff"))
                )
            ) {
                val infiniteTransitions = rememberInfiniteTransition(label = "InfiniteTransitions")
                val angle by infiniteTransitions.animateFloat(
                    initialValue = 360F,
                    targetValue = 0F,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            800,
                            easing = LinearEasing
                        )
                    ),
                    label = "FloatingAnimation"
                )
                LoadingIcon(
                    modifier = Modifier.graphicsLayer { rotationZ = angle }
                )
            }
        }
    }
}

@Composable
private fun LoadingIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.rotate_right),
        contentDescription = "loading",
        modifier = modifier
    )
}

const val LOADING_TAG = "loading"