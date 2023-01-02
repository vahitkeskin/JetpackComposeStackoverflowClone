package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import android.text.Html
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowExpandableText
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.QuestionsViewModel
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun QuestionsScreen(
    questionsViewModel: QuestionsViewModel = hiltViewModel(),
) {
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
        Box(Modifier.background(DarkPurple)) {
            if (isMenuOpened) {
                SideMenuOptions(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-100).dp)
                )
                SearchBar(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 100.dp, bottom = 16.dp)
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
                    text = item.title.toString(),
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

@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(54.dp)
            .background(Color.Gray, RoundedCornerShape(percent = 50)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(16.dp))
        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
    }
}

@Composable
private fun SideMenuOptions(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        MenuOption(R.drawable.ic_video_library, contentDescription = "Go to Video Library")
        MenuOption(R.drawable.ic_image, contentDescription = "Go to Pictures")
        MenuOption(R.drawable.ic_camera, contentDescription = "Open Camera")
    }
}

@Composable
private fun MenuOption(@DrawableRes icon: Int, contentDescription: String) {
    IconButton(
        modifier = Modifier
            .size(56.dp)
            .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(2.dp)),
        onClick = { }
    ) {
        Icon(painterResource(icon), contentDescription, tint = Color.White)
    }
}

private val LightPurple = Color(0xFF6200EE)
private val Purple = Color(0xFF3700B3)
private val DarkPurple = Color(0xFF25073B)
private val Pink = Color(0xFFE32677)

private val DarkColorPalette = darkColors(
    primary = LightPurple,
    primaryVariant = Purple,
    secondary = Pink,
    onSecondary = Color.White,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = LightPurple,
    primaryVariant = Purple,
    secondary = Pink,
    background = Color.LightGray,
    surface = Color.White,
    onSecondary = Color.White,
)

@Composable
fun QuestionsChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    QuestionsChallengeTheme {
        QuestionsScreen()
    }
}
