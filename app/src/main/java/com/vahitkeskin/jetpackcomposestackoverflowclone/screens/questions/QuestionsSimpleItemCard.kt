package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowExpandableText
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@Composable
fun QuestionsSimpleItemCard(item: Item) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(
                bottom = 15.dp,
                start = 15.dp,
                end = 15.dp
            )
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
                    text = "${item.score} votes",
                    color = Color.LightGray
                )
                Text(
                    text = "${item.answer_count} answers",
                    color = Color.Gray
                )
                Text(
                    text = "${item.view_count} views",
                    color = Color.Gray
                )
            }

            Column {
                TextFlow(
                    color = StackoverflowBlue,
                    text = item.title,
                    modifier = Modifier.fillMaxWidth(),
                    obstacleAlignment = TextFlowObstacleAlignment.TopStart,
                    obstacleContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_quesions_search_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                )
                StackoverflowExpandableText(
                    text = Html.fromHtml(item.body).toString(),
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