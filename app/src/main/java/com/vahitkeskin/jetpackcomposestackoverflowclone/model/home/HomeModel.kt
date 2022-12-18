package com.vahitkeskin.jetpackcomposestackoverflowclone.model.home

data class HomeModel(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)