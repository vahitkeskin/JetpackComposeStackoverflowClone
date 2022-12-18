package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.encode
import com.vahitkeskin.jetpackcomposestackoverflowclone.views.NavigationItem

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@Composable
fun HomeScreenItem(
    navController: NavController,
    item: Item
) {
    Card(
        modifier = Modifier.padding(15.dp).clickable {
            navController.navigate(
                route = NavigationItem.HomeDetail.route.plus("/${item.title.encode()}")
            )
        },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = item.title, color = StackoverflowBlue)
            LazyRow(
                modifier = Modifier.padding(end = 5.dp, top = 10.dp)
            ) {
                items(item.tags) { mItem ->
                    HomeScreenTagsItem(tags = mItem)
                }
            }
            HomeScreenOwner(item)
        }
    }
}