package com.huda.blogsapp.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAuthorListShimmer(
    cardHeight: Dp,
    itemsCount: Int,
    padding: Dp = 8.dp
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth - (padding * 2)).toPx() }
        val cardHeightPx = with(LocalDensity.current) { (cardHeight - padding).toPx() }
        val gradientWidth: Float = (3.0f * cardHeightPx)

        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWidthPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )


        val colors = listOf(
            Color.LightGray.copy(alpha = .9f),
            Color.LightGray.copy(alpha = .5f),
            Color.LightGray.copy(alpha = .9f),
        )

        LazyColumn {
            items(itemsCount) {
                ShimmerAuthorCardItem(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    cardHeight = cardHeight,
                    gradientWidth = gradientWidth,
                    padding = padding
                )
            }
        }
    }


}