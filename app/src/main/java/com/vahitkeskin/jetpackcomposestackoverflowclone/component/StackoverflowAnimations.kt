package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@ExperimentalAnimationApi
fun downToUpAnim(duration: Int = 800): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

@ExperimentalAnimationApi
fun upToDownAnim(duration: Int = 800): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}