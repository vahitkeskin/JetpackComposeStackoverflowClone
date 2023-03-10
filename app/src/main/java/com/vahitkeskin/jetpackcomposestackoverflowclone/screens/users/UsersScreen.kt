package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowGifIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointUnSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.UsersViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun UsersScreen(
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    var state by remember { mutableStateOf(true) }
    val homeModelState = remember { mutableStateListOf<Item>() }
    var searchState by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var job: Job? = null
    var newSearhState by remember { mutableStateOf(false) }

    fun searchQuery(searchQuery: String ?= null) {
        coroutineScope.launch {
            homeModelState.clear()
            usersViewModel.home(searchQuery.orEmpty()).items.forEach { item ->
                homeModelState.add(item)
            }
        }
    }

    if (state) {
        searchQuery()
        state = false
    }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp),
            value = searchState,
            onValueChange = { newText ->
                newSearhState = true
                selectedIndex = -1 //A new search was made. Close open animations
                if (newText.isEmpty()) {
                    searchQuery()
                } else {
                    searchState = newText
                    job?.cancel()
                    job = coroutineScope.launch {
                        delay(1000)
                        searchQuery(searchState)
                    }
                }
            },
            label = {
                Text(
                    text = Contains.FILTER_BY_USER,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences //Keyboard first letter uppercase
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            ),
            leadingIcon = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (homeModelState.size > 0) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    } else {
                        if (!newSearhState) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(10.dp),
                                strokeWidth = 2.dp,
                                color = StackoverflowPointUnSelect
                            )
                        }
                    }
                }
            },
            trailingIcon = {
                if (searchState.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            searchState = ""
                            selectedIndex = -1 //A new search was made. Close open animations
                            searchQuery()
                        },
                        painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                        contentDescription = null,
                        tint = Color.LightGray,
                    )
                }
            }
        )
        if (homeModelState.size > 0) {
            LazyColumn {
                itemsIndexed(homeModelState) { index, item ->
                    UsersItem(
                        item = item,
                        selected = selectedIndex == index,
                        clickAction = { selectedIndex = index }
                    )
                }
            }
        } else {
            if (newSearhState) {
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