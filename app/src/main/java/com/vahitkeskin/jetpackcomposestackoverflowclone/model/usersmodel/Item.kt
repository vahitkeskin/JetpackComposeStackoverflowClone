package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

data class Item(
    val accept_rate: Int,
    val account_id: Int,
    val badge_counts: BadgeCounts,
    val collectives: List<Collective>,
    val creation_date: Int,
    val display_name: String,
    val is_employee: Boolean,
    val last_access_date: Int,
    val last_modified_date: Int,
    val link: String,
    val about_me: String,
    val location: String,
    val profile_image: String,
    val reputation: Int,
    val reputation_change_day: Int,
    val reputation_change_month: Int,
    val reputation_change_quarter: Int,
    val reputation_change_week: Int,
    val reputation_change_year: Int,
    val user_id: Int,
    val user_type: String,
    val website_url: String
)