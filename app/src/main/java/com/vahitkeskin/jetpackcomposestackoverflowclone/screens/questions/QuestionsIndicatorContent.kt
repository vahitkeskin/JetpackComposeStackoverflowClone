package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointSelect

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */
@Composable
fun QuestionsIndicatorContent(
    isThumbSelected: Boolean,
    item: Item
) {
    Surface(
        modifier = Modifier
            .border(
                1.dp, StackoverflowPointSelect, RoundedCornerShape(
                    topStart = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 16.dp
                )
            )
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 16.dp
                )
            )
    ) {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .padding(8.dp)
                .clip(CircleShape)
                .background(if (isThumbSelected) MaterialTheme.colors.surface else Color.DarkGray)
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 5.dp),
                painter = rememberAsyncImagePainter(
                    model = item.owner.profile_image
                ),
                contentDescription = null
            )
            Text(
                text = item.owner.display_name,
                color = StackoverflowBlue
            )
        }
    }
}