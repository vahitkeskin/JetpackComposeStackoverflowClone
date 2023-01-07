package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowPostUserInfo
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item

/**
 * @authot: Vahit Keskin
 * creared on 3.01.2023
 */
@Composable
fun QuestionsScreenOwner(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(20.dp)
                .padding(end = 5.dp),
            painter = rememberAsyncImagePainter(model = item.owner.profile_image),
            contentDescription = null
        )
        StackoverflowPostUserInfo(item)
    }
}