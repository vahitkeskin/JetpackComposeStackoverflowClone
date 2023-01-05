package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette
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
    var palette by remember { mutableStateOf<Palette?>(null) }
    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = palette?.darkVibrantSwatch?.let { Color(it.rgb) } ?: StackoverflowPointSelect,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
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
            GlideImage(
                contentScale = ContentScale.Crop,
                imageModel = item.owner.profile_image,
                bitmapPalette = BitmapPalette { palette = it },
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = item.owner.display_name,
                color = StackoverflowBlue
            )
        }
    }
}