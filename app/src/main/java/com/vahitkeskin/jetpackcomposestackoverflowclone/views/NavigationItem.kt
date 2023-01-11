package com.vahitkeskin.jetpackcomposestackoverflowclone.views

import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
sealed class NavigationItem(
    var route: String, var icon: Int?, var title: String
) {
    object Home : NavigationItem(
        route = Contains.HOME_SCREEN,
        icon = R.drawable.ic_home_white,
        title = Contains.NAVIGATION_ITEM_HOME
    )

    object Questions : NavigationItem(
        route = Contains.QUESTIONS_SCREEN,
        icon = R.drawable.ic_search_questions_white,
        title = Contains.NAVIGATION_ITEM_QUESTIONS
    )

    object Users : NavigationItem(
        route = Contains.USERS_SCREEN,
        icon = R.drawable.ic_search_users_white,
        title = Contains.NAVIGATION_ITEM_USERS
    )

    object HomeDetail : NavigationItem(
        route = Contains.HOME_DETAIL_SCREEN,
        icon = null,
        title = Contains.NAVIGATION_ITEM_HOME_DETAIL
    )
}