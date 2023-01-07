package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collective(
    val collective: CollectiveX,
    val role: String
) : Parcelable