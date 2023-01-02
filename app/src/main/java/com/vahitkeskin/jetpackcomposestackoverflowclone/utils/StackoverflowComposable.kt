package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.*
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home.HomeScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.homedetail.HomeDetailScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions.QuestionsScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users.UsersScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Utility.toJson
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.QuestionsViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.UsersViewModel
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
    val usersViewModel: UsersViewModel = hiltViewModel()
    val questionsViewModel: QuestionsViewModel = hiltViewModel()
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
                QuestionsScreen(questionsViewModel = questionsViewModel)
            }
        )
        composable(
            route = NavigationItem.Users.route,
            content = {
                UsersScreen(usersViewModel = usersViewModel)
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