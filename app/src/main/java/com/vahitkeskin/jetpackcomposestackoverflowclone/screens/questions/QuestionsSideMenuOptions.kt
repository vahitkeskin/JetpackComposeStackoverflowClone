package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.downToUpAnim
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.upToDownAnim

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuestionsSideMenuOptions(
    modifier: Modifier = Modifier,
    selectSearchItemSizeState: (Int, Boolean) -> Unit
) {
    var stateNumber by remember { mutableStateOf(100) }
    var isUpOrDown by remember { mutableStateOf(true) }
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(2.dp)),
            onClick = {
                stateNumber += 1
                isUpOrDown = true
            },
            content = {
                Icon(painterResource(R.drawable.ic_point_up), null, tint = Color.White)
            }
        )
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(2.dp)),
            onClick = { },
            content = {
                AnimatedContent(
                    targetState = stateNumber,
                    transitionSpec = {
                        if (isUpOrDown) {
                            downToUpAnim().using(
                                SizeTransform(clip = false)
                            )
                        } else {
                            upToDownAnim().using(
                                SizeTransform(clip = false)
                            )
                        }
                    }
                ) { targetCount ->
                    Text(text = if (targetCount < 10) "0$targetCount" else targetCount.toString())
                }
            }
        )
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(2.dp)),
            onClick = {
                if (stateNumber > 0) {
                    stateNumber -= 1
                    isUpOrDown = false
                }
            },
            content = {
                Icon(painterResource(R.drawable.ic_point_down), null, tint = Color.White)
            }
        )
    }
    selectSearchItemSizeState.invoke(stateNumber, isUpOrDown)
}