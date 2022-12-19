package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.StackoverflowComposable
import com.vahitkeskin.jetpackcomposestackoverflowclone.views.BottomBarState

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun StackoverflowScaffold() {
    val navHostController = rememberNavController()
    val bottomBarState = BottomBarState(navController = navHostController)

    Scaffold(
        topBar = {
            StackoverflowTopAppBar(
                isBackButton = bottomBarState.value,
                navController = navHostController
            )
        },
        bottomBar = {
            StackoverflowBottomBar(
                navController = navHostController,
                bottomBarState = bottomBarState
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                StackoverflowComposable(navController = navHostController)
            }
        }
    )
}