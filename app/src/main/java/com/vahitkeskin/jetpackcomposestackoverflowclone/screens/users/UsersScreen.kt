package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowCodeBg
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointUnSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.UsersViewModel

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun UsersScreen(
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    var state by remember { mutableStateOf(true) }
    val homeModelState = remember { mutableStateListOf<Item>() }
    var searchState by remember { mutableStateOf(TextFieldValue("")) }

    if (state) {
        usersViewModel.usersModel.observeForever { homeModel ->
            homeModel.items.forEach {
                homeModelState.add(it)
            }
        }
        state = false
    }
    Column {
        BasicTextField(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp)
                .border(1.dp, StackoverflowPointUnSelect, RoundedCornerShape(5.dp)),
            value = searchState,
            textStyle = MaterialTheme.typography.body1.copy(color = Color.LightGray),
            onValueChange = { searchState = it },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(StackoverflowCodeBg, RoundedCornerShape(percent = 30))
                        .padding(14.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.ic_search_questions_white),
                        contentDescription = null,
                        tint = StackoverflowPointUnSelect
                    )
                    if (searchState.text.isEmpty()) {
                        Text("Filter by user", color = StackoverflowPointUnSelect)
                    }
                    innerTextField()
                }
            }
        )
        if (homeModelState.size > 0) {
            LazyColumn {
                items(homeModelState) { item ->
                    UsersItem(item)
                }
            }
        }
    }
}

@Composable
fun UsersItem(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp)),
                painter = rememberAsyncImagePainter(model = item.profile_image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Column {
                Text(text = item.display_name.orEmpty(), color = StackoverflowBlue)
                Text(text = item.location.orEmpty())
                Text(text = item.reputation.toString().orEmpty())
            }
        }
    }
}