package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.Item

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */

@Composable
fun QuestionsMainContent(
    modifier: Modifier = Modifier,
    questionModelState: SnapshotStateList<Item>
) {
    LazyColumn(modifier.background(MaterialTheme.colors.background)) {
        items(questionModelState) { item ->
            QuestionsSimpleItemCard(item)
        }
    }
}