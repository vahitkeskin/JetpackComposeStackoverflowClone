package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item

data class UsersModel(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)