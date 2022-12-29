package com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
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