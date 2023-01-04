package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowGifIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.JetpackComposeStackoverflowCloneTheme
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.QuestionsViewModel
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
    var searchState by remember { mutableStateOf("Android Jetpack Compose") }
    if (state) {
        coroutineScope.launch {
            questionsViewModel.home(searchState)
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
            QuestionsFloatingAddButton(
                isOpened = isMenuOpened,
                onClick = { isMenuOpened = !isMenuOpened },
            )
        }
    ) {
        Box(Modifier.background(Color(0xFF25073B))) {
            if (isMenuOpened) {
                QuestionsSideMenuOptions(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-100).dp),
                    selectSearchItemSizeState = { mMaxCharState, mIsSelectItemSizeState ->
                        maxCharState = mMaxCharState
                        isSelectItemSizeState = mIsSelectItemSizeState
                    }
                )
                QuestionsSearchBar(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 100.dp, bottom = 16.dp),
                    maxChar = maxCharState,
                    isUpOrDown = isSelectItemSizeState,
                    search = {
                        questionModelState.clear()
                        searchState = it
                        state = true
                    }
                )
            }
            val contentOffset = animateDpAsState(if (isMenuOpened) (-100).dp else 0.dp)
            val corner = animateDpAsState(if (isMenuOpened) 24.dp else 0.dp)
            Column {
                if (questionModelState.size > 0) {
                    QuestionsMainContent(
                        questionModelState = questionModelState,
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(contentOffset.value, contentOffset.value)
                            .clip(RoundedCornerShape(corner.value))
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        StackoverflowGifIcon(
                            icon = R.drawable.no_search_results_found_gif,
                            previewPage = Contains.QUESTION_SCREEN_ITEM_SIZE
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    JetpackComposeStackoverflowCloneTheme() {
        QuestionsScreen()
    }
}
