package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.util.Base64
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains.UTF_8

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
fun String.encode(): String {
    //Before
    return Base64.encodeToString(this.toByteArray(charset(UTF_8)), Base64.DEFAULT)
}

fun String.decode(): String {
    //After
    return Base64.decode(this, Base64.DEFAULT).toString(charset(UTF_8))
}