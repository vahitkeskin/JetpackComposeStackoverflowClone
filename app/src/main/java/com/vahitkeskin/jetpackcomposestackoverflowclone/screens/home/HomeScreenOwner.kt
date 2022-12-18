package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@Composable
fun HomeScreenOwner(item: Item) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(20.dp).padding(end = 5.dp),
            painter = rememberAsyncImagePainter(model = item.owner.profile_image),
            contentDescription = null
        )
        Text(text = item.owner.display_name, color = StackoverflowBlue)
    }
}