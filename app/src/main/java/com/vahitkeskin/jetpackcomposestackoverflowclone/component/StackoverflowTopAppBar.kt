package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@Composable
fun StackoverflowTopAppBar() {
    TopAppBar {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.stackoverflow_logo),
                contentDescription = null
            )
        }
    }
}