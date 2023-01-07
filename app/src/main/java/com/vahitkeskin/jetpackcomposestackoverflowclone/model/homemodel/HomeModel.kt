package com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item

data class HomeModel(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)