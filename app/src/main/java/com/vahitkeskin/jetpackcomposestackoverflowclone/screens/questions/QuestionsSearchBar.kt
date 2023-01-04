package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowGifIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.downToUpAnim
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.upToDownAnim
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuestionsSearchBar(
    modifier: Modifier = Modifier,
    maxChar: Int? = 0,
    isUpOrDown: Boolean? = true,
    search: (String) -> Unit
) {
    var searchState by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf("") }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = text,
                    onValueChange = {
                        if (it.length <= (maxChar ?: 0)) text = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp, horizontal = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = {
                        if (text.isNotEmpty()) {
                            Icon(
                                modifier = Modifier.clickable {
                                    text = ""
                                },
                                painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                                contentDescription = null,
                                tint = Color.LightGray,
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.DarkGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    maxLines = 1,
                    singleLine = true
                )
                if (text.isNotEmpty()) {
                    FloatingActionButton(
                        onClick = {
                            //Remove leading and trailing whitespace from a string in Kotlin
                            text = text.trim()
                            searchState = false
                        },
                        backgroundColor = Color.DarkGray
                    ) {
                        if (searchState) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                        } else {
                            StackoverflowGifIcon(
                                icon = R.drawable.ic_question_search_bar_icon_gif,
                                previewPage = Contains.QUESTION_SCREEN_FAB
                            )
                            coroutineScope.launch {
                                delay(3000)
                                searchState = true
                                search.invoke(text)
                            }
                        }

                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = if (text.isNotEmpty()) 60.dp else 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${text.length} / ",
                    textAlign = TextAlign.End,
                    color = Color.LightGray,
                    style = MaterialTheme.typography.caption,
                )
                AnimatedContent(
                    targetState = maxChar,
                    transitionSpec = {
                        if (isUpOrDown == true) {
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
                    Text(
                        text = if ((targetCount
                                ?: 0) < 10
                        ) "0$targetCount" else targetCount.toString(),
                        textAlign = TextAlign.End,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}