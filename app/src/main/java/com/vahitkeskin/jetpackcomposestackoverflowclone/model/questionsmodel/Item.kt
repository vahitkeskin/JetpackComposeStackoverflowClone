package com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel

data class Item(
    val answer_count: Int,
    val body: String,
    val content_license: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val link: String,
    val owner: Owner,
    val question_id: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val view_count: Int
)