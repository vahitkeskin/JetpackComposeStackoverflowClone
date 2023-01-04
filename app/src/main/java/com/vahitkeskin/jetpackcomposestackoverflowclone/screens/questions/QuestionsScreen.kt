package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import android.text.Html
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowExpandableText
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowGifIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.JetpackComposeStackoverflowCloneTheme
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.QuestionsViewModel
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun QuestionsScreen(
    questionsViewModel: QuestionsViewModel = hiltViewModel(),
) {
    var maxCharState by remember { mutableStateOf(100) }
    var isSelectItemSizeState by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    var state by remember { mutableStateOf(true) }
    val questionModelState = remember { mutableStateListOf<Item>() }
    if (state) {
        coroutineScope.launch {
            questionsViewModel.home("Android Jetpack Compose")
                .items.forEach {
                    questionModelState.add(it)
                }
        }
        state = false
    }

    var isMenuOpened by mutableStateOf(false)
    Scaffold(
        topBar = { },
        floatingActionButton = {
            FloatingAddButton(
                isOpened = isMenuOpened,
                onClick = { isMenuOpened = !isMenuOpened },
            )
        }
    ) {
        Box(Modifier.background(Color(0xFF25073B))) {
            if (isMenuOpened) {
                SideMenuOptions(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-100).dp),
                    selectSearchItemSizeState = { mMaxCharState, mIsSelectItemSizeState ->
                        maxCharState = mMaxCharState
                        isSelectItemSizeState = mIsSelectItemSizeState
                    }
                )
                SearchBar(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 100.dp, bottom = 16.dp),
                    maxChar = maxCharState,
                    isUpOrDown = isSelectItemSizeState
                )
            }
            val contentOffset = animateDpAsState(if (isMenuOpened) (-100).dp else 0.dp)
            val corner = animateDpAsState(if (isMenuOpened) 24.dp else 0.dp)
            Column {
                if (questionModelState.size > 0) {
                    MainContent(
                        questionModelState = questionModelState,
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(contentOffset.value, contentOffset.value)
                            .clip(RoundedCornerShape(corner.value))
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingAddButton(isOpened: Boolean, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color.Transparent
    ) {
        val rotation = animateFloatAsState(
            targetValue = if (isOpened) -180f else 0f,
            animationSpec = spring(DampingRatioHighBouncy)
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

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    questionModelState: SnapshotStateList<Item>
) {
    LazyColumn(modifier.background(MaterialTheme.colors.background)) {
        items(questionModelState) { item ->
            SimpleItemCard(item)
        }
    }
}

@Composable
private fun SimpleItemCard(item: Item) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.padding(end = 10.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${item.score} votes",
                    color = Color.LightGray
                )
                Text(
                    text = "${item.answer_count} answers",
                    color = Color.Gray
                )
                Text(
                    text = "${item.view_count} views",
                    color = Color.Gray
                )
            }

            Column {
                TextFlow(
                    color = StackoverflowBlue,
                    text = item.title,
                    modifier = Modifier.fillMaxWidth(),
                    obstacleAlignment = TextFlowObstacleAlignment.TopStart,
                    obstacleContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_quesions_search_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                )
                StackoverflowExpandableText(
                    text = Html.fromHtml(item.body).toString(),
                    modifier = Modifier.padding(8.dp),
                    color = Color.LightGray
                )
                LazyRow(
                    modifier = Modifier.padding(end = 5.dp, top = 10.dp)
                ) {
                    items(item.tags) { mItem ->
                        QuestionScreenTagsItem(tags = mItem)
                    }
                }
                QuestionScreenOwner(item)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    maxChar: Int? = 0,
    isUpOrDown: Boolean? = true
) {
    var searchState by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
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
                            StackoverflowGifIcon(icon = R.drawable.ic_question_search_bar_icon_gif)
                            coroutineScope.launch {
                                delay(3000)
                                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                                searchState = true
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SideMenuOptions(
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


@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    JetpackComposeStackoverflowCloneTheme() {
        QuestionsScreen()
    }
}
