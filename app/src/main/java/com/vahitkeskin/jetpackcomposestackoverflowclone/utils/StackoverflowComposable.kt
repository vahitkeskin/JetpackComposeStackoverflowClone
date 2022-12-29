package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.*
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home.HomeScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.homedetail.HomeDetailScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions.QuestionsScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users.UsersScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Utility.toJson
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
        composable(
            route = NavigationItem.Home.route
        ) {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                navigateToDetail = {
                    navController.navigate(NavigationItem.HomeDetail.route.plus("?homeDetail=${it.toJson()}"))
                }
            )
        }
        composable(
            route = NavigationItem.Questions.route,
            content = {
                QuestionsScreen()
            }
        )
        composable(
            route = NavigationItem.Users.route,
            content = {
                UsersScreen()
            }
        )
        composable(
            NavigationItem.HomeDetail.route.plus("?homeDetail={homeDetail}"),
            content = {
                HomeDetailScreen(viewModel = hiltViewModel())
            }
        )
    }

}