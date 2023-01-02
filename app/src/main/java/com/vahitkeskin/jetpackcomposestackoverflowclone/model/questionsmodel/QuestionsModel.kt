package com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel

data class QuestionsModel(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)