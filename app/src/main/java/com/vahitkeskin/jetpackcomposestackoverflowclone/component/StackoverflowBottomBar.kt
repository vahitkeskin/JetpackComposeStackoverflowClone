package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowOrange
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.views.NavigationItem

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun StackoverflowBottomBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    var clickUserIcon by remember { mutableStateOf(true) }
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Questions,
        NavigationItem.Users,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            item.icon?.let { icon ->
                                if (clickUserIcon && item.route == NavigationItem.Users.route) {
                                    StackoverflowGifIcon(
                                        icon = R.drawable.user_search_gif1,
                                        previewPage = Contains.BOTTOM_BAR_USERS_ICON
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.padding(
                                            bottom = if (currentRoute == item.route) 4.dp else 0.dp
                                        ),
                                        painter = painterResource(id = icon),
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        label = {
                            if (currentRoute == item.route) {
                                Card(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20))
                                        .background(StackoverflowOrange),
                                    content = {
                                        Text(
                                            modifier = Modifier
                                                .background(StackoverflowOrange)
                                                .padding(
                                                    start = 5.dp,
                                                    end = 5.dp
                                                ),
                                            text = item.title
                                        )
                                    }
                                )
                            } else {
                                Text(text = item.title)
                            }
                        },
                        selected = currentRoute == item.route,
                        selectedContentColor = Color.LightGray,
                        unselectedContentColor = Color.Gray,
                        onClick = {
                            clickUserIcon = item.route != NavigationItem.Users.route
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}