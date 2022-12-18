package com.vahitkeskin.jetpackcomposestackoverflowclone.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.BottomBarState
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.StackoverflowComposable

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun BottomBarStackOverflow() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                bottomBarState = BottomBarState(navController = navController)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                StackoverflowComposable(navController = navController)
            }
        }
    )
}