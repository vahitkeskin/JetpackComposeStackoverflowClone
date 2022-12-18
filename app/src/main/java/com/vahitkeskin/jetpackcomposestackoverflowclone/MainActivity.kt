package com.vahitkeskin.jetpackcomposestackoverflowclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.HomeDetailScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.HomeScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.QuestionsScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.UsersScreen
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.JetpackComposeStackoverflowCloneTheme
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarStackOverflow()
        }
    }
}

@Composable
fun BottomBarStackOverflow() {
    val bottomBarState = rememberSaveable { mutableStateOf(true) }
    JetpackComposeStackoverflowCloneTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

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
            Contains.HOME_DETAIL_SCREEN -> {
                bottomBarState.value = false
            }
        }

        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = NavigationItem.Home.route
                ) {
                    composable(NavigationItem.Home.route) {
                        HomeScreen(navController = navController)
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
        )

        /*
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Greeting("Android")
        }
        */
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStackoverflowCloneTheme {
        Greeting("Android")
    }
}