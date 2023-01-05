package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.datastore.SharedDataStore
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.SecondsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuestionsZoomScreenSkip(
    mainViewModel: SecondsViewModel = viewModel(),
    skipTextSize: TextUnit = 12.sp
) {
    val seconds by mainViewModel.seconds.collectAsState(initial = "10")
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStore = SharedDataStore(context)
    Row (
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "It will turn off automatically after ",
            color = Color.Gray,
            fontSize = skipTextSize
        )
        AnimatedContent(
            targetState = seconds,
            transitionSpec = {
                upToDownAnim().using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = targetCount.toString(),
                color = Color.LightGray,
                fontSize = skipTextSize
            )
            if (targetCount.toString().toIntOrNull() == 0) {
                coroutineScope.launch {
                    delay(1000)
                    dataStore.saveQuestionsScreenSwitchButtonZoom(false)
                }
            }
        }
        Text(
            text = " seconds",
            color = Color.Gray,
            fontSize = skipTextSize
        )
    }
}