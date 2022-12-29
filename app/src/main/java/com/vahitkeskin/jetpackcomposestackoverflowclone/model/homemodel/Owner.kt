package com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val account_id: Int,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
) : Parcelable