package com.vahitkeskin.jetpackcomposestackoverflowclone.model

import android.os.Parcelable
import com.google.gson.Gson
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.Owner
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.BadgeCounts
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.Collective
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val title: String,
    val accept_rate: Int,
    val account_id: Int,
    val badge_counts: BadgeCounts,
    val collectives: List<Collective>,
    val creation_date: Int,
    val display_name: String,
    val is_employee: Boolean,
    val last_access_date: Int,
    val last_modified_date: Int,
    val last_activity_date: Long,
    val last_edit_date: Long,
    val owner: Owner,
    val tags: List<String>,
    val score: Int,
    val body: String,
    val answer_count: Int,
    val view_count: Int,
    val link: String,
    val about_me: String,
    val location: String,
    val profile_image: String,
    val reputation: Int,
    val reputation_change_day: Int,
    val reputation_change_month: Int,
    val reputation_change_quarter: Int,
    val reputation_change_week: Int,
    val reputation_change_year: Int,
    val user_id: Int,
    val user_type: String,
    val website_url: String
) : Parcelable {
    companion object {
        fun create(jsonString: String): Item? {
            return try {
                Gson().fromJson(jsonString, Item::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}