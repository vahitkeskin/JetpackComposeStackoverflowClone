package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@Composable
fun HomeScreenItem(
    item: Item,
    navigateToDetail: (Item) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp)
            .clickable {
                navigateToDetail.invoke(item)
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
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