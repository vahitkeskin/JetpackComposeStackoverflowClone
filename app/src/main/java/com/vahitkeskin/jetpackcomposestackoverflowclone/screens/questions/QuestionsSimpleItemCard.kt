package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowExpandableText
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.startDrawableIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.startDrawableText

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@Composable
fun QuestionsSimpleItemCard(
    item: Item,
    navigateToDetail: (Item) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(
                bottom = 15.dp,
                start = 15.dp,
                end = 15.dp
            )
            .clickable {
                navigateToDetail.invoke(item)
            }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.padding(end = 10.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.score.toString() + Contains.VOTES,
                    color = Color.LightGray
                )
                Text(
                    text = item.answer_count.toString() + Contains.ANSWERS,
                    color = Color.Gray
                )
                Text(
                    text = item.view_count.toString() + Contains.VIEWS,
                    color = Color.Gray
                )
            }

            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = Contains.STARTDRAWABLE_ICON_AND_TEXT_ID.startDrawableText(item.title),
                    color = StackoverflowBlue,
                    inlineContent = Contains.STARTDRAWABLE_ICON_AND_TEXT_ID.startDrawableIcon(R.drawable.ic_quesions_search_icon)
                )
                StackoverflowExpandableText(
                    text = HtmlCompat.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString(),
                    modifier = Modifier.padding(8.dp),
                    color = Color.LightGray
                )
                LazyRow(
                    modifier = Modifier.padding(end = 5.dp, top = 10.dp)
                ) {
                    items(item.tags) { mItem ->
                        QuestionsScreenTagsItem(tags = mItem)
                    }
                }
                QuestionsScreenOwner(item)
            }
        }
    }
}