package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

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
import androidx.compose.ui.unit.sp
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowTagsBg
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowTagsText

/**
 * @authot: Vahit Keskin
 * creared on 3.01.2023
 */

@Composable
fun QuestionScreenTagsItem(tags: String) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .background(StackoverflowTagsBg)
            .padding(top = 2.dp, bottom = 2.dp, end = 4.dp, start = 4.dp)
    ) {
        Text(
            modifier = Modifier.background(StackoverflowTagsBg),
            text = tags,
            color = StackoverflowTagsText,
            fontSize = 14.sp
        )
    }
    Spacer(modifier = Modifier.padding(end = 5.dp))
}