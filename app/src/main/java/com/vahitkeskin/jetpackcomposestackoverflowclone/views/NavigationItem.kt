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
    object Home: NavigationItem(Contains.HOME_SCREEN, R.drawable.ic_home_white,"Home")
    object Questions: NavigationItem(Contains.QUESTIONS_SCREEN, R.drawable.ic_search_questions_white,"Questions")
    object Users: NavigationItem(Contains.USERS_SCREEN, R.drawable.ic_search_users_white,"Users")
    object HomeDetail: NavigationItem(Contains.HOME_DETAIL_SCREEN,null,"Home Detail")
}