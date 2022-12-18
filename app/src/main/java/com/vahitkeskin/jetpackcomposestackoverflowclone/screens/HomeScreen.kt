package com.vahitkeskin.jetpackcomposestackoverflowclone.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Button(
                content = { Text(text = "Open Home Detail") },
                onClick = { navController.navigate("home_detail") }
            )
            Text(
                text = "Home Screen",
                modifier = Modifier.padding(6.dp)
            )
        }
    )
}