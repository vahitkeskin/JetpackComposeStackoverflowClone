package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.*
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.views.NavigationItem

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@SuppressLint("UnrememberedMutableState")
@Composable
fun StackoverflowComposable(
    navController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable(NavigationItem.Questions.route) {
            QuestionsScreen()
        }
        composable(NavigationItem.Users.route) {
            UsersScreen()
        }
        composable(NavigationItem.HomeDetail.route) {
            HomeDetailScreen()
        }
    }

}