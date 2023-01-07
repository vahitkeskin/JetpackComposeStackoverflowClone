package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeViewModel


/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Item) -> Unit
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
            items(homeModelState) { item ->
                HomeScreenItem(item) {
                    navigateToDetail.invoke(it)
                }
            }
        }
    }
}