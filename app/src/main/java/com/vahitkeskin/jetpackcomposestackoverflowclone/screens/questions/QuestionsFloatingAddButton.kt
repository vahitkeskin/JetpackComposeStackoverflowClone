package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointSelect

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */
@Composable
fun QuestionsFloatingAddButton(isOpened: Boolean, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color.Transparent
    ) {
        val rotation = animateFloatAsState(
            targetValue = if (isOpened) -180f else 0f,
            animationSpec = spring(Spring.DampingRatioHighBouncy)
        )
        Icon(
            painter = painterResource(id = R.drawable.loading_113629),
            contentDescription = "Search button",
            modifier = Modifier
                .rotate(rotation.value)
                .size(50.dp),
            tint = StackoverflowPointSelect
        )
    }
}