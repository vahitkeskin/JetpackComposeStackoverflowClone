package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExternalLink(
    val link: String,
    val type: String
) : Parcelable