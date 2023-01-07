package com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BadgeCounts(
    val bronze: Int,
    val gold: Int,
    val silver: Int
) : Parcelable