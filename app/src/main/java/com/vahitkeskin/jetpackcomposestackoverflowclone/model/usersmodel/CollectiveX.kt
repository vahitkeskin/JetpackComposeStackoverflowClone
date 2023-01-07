package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectiveX(
    val description: String,
    val external_links: List<ExternalLink>,
    val link: String,
    val name: String,
    val slug: String,
    val tags: List<String>
) : Parcelable