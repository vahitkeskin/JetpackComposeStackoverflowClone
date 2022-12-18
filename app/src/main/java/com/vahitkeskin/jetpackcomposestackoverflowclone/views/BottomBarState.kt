package com.vahitkeskin.jetpackcomposestackoverflowclone.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun BottomBarState(
    navController: NavController
) : MutableState<Boolean> {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { mutableStateOf(true) }
    when (navBackStackEntry?.destination?.route) {
        Contains.HOME_SCREEN -> {
            bottomBarState.value = true
        }
        Contains.QUESTIONS_SCREEN -> {
            bottomBarState.value = true
        }
        Contains.USERS_SCREEN -> {
            bottomBarState.value = true
        }
        else -> {
            bottomBarState.value = false
        }
    }
    return bottomBarState
}