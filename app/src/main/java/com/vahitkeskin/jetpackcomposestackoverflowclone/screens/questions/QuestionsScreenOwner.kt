package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue

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
        Text(text = item.owner.display_name, color = StackoverflowBlue)
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = item.owner.reputation.toString(),
            fontSize = 12.sp,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold
        )
    }
}