package com.vahitkeskin.jetpackcomposestackoverflowclone.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.home.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.home.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeViewModel

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var state by remember { mutableStateOf(true) }
    val homeModelState = remember { mutableStateListOf<Item>() }
    if (state) {
        homeViewModel.homeModel.observeForever { homeModel ->
            homeModel.items.forEach {
                homeModelState.add(it)
            }
        }
        state = false
    }
    if (homeModelState.size > 0) {
        LazyColumn {
            itemsIndexed(homeModelState) { index, item ->
                HomeScreenItem(index,item)
            }
        }
    }

    /*
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Button(
                content = { Text(text = "Open Home Detail") },
                onClick = { navController.navigate(NavigationItem.HomeDetail.route) }
            )
            Text(
                text = "Home Screen",
                modifier = Modifier.padding(6.dp)
            )
        }
    )
    */
}

@Composable
fun HomeScreenItem(position:Int, item: Item) {
    println("$position HomeScreenItem item title: ${item.title}")
    Card(
        modifier = Modifier.padding(15.dp),
        elevation = 10.dp
    ) {
        Column {
            Text(text = "$position. " + item.title, color = StackoverflowBlue)
            Text(text = "$position. TAGS " + item.tags, color = StackoverflowBlue)
        }
    }
}