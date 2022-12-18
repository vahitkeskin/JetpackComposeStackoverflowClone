package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.homedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun HomeDetailScreen(title: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title.orEmpty())
    }
}