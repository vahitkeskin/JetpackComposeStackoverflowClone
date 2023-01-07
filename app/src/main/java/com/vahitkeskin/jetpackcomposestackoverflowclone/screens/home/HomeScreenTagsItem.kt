package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowTagsBg
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowTagsText

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@Composable
fun HomeScreenTagsItem(tags: String) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .background(StackoverflowTagsBg)
    ) {
        Text(
            modifier = Modifier
                .background(StackoverflowTagsBg)
                .padding(top = 4.dp, bottom = 4.dp, end = 8.dp, start = 8.dp),
            text = tags,
            color = StackoverflowTagsText
        )
    }
    Spacer(modifier = Modifier.padding(end = 5.dp))
}