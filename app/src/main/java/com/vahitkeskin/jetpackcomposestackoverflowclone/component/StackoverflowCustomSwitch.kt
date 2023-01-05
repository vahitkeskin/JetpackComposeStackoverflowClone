package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.datastore.SharedDataStore
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
@Composable
fun StackoverflowCustomSwitch(
    width: Dp = 52.dp,
    height: Dp = 40.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
    borderWidth: Dp = 1.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 4.dp,
    thumbSize: Dp = 24.dp
) {
    val context = LocalContext.current
    val dataStore = SharedDataStore(context)
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val value = dataStore.getScrollbarDetail.collectAsState(initial = "").value
    var switchOn by remember {
        mutableStateOf(value)
    }
    val alignment by animateAlignmentAsState(if (switchOn == true) 1f else -1f)
    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                width = borderWidth,
                color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                shape = RoundedCornerShape(percent = cornerSize)
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                coroutineScope.launch {
                    switchOn = switchOn != true
                    if (switchOn == true) {
                        dataStore.saveScrollbarDetail(true)
                    } else {
                        dataStore.saveScrollbarDetail(false)
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge,
                    top = gapBetweenThumbAndTrackEdge,
                    bottom = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            Icon(
                imageVector = if (switchOn == true) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (switchOn == true) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = thumbSize)
                    .background(
                        color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                        shape = CircleShape
                    )
                    .padding(all = iconInnerPadding),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}