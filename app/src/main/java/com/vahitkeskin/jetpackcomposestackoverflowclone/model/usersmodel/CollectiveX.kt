package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

data class CollectiveX(
    val description: String,
    val external_links: List<ExternalLink>,
    val link: String,
    val name: String,
    val slug: String,
    val tags: List<String>
)