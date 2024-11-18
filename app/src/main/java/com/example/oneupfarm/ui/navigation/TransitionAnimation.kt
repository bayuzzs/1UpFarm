package com.example.oneupfarm.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

private const val ANIMATION_DURATION = 1000

internal fun slideInTransition() = slideInVertically(
    initialOffsetY = { it },
    animationSpec = tween(ANIMATION_DURATION)
)

internal fun slideOutTransition() = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = tween(ANIMATION_DURATION)
)

internal fun slideInTransitionPop() = slideInVertically(
    initialOffsetY = { -it },
    animationSpec = tween(ANIMATION_DURATION)
)

internal fun slideOutTransitionPop() = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = tween(ANIMATION_DURATION)
)

