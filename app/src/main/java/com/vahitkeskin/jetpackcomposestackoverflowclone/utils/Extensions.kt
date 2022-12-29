package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.content.res.Resources
import android.util.Base64
import android.util.DisplayMetrics
import android.view.Display
import android.view.Window
import android.view.WindowManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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

/**
 * noRippleClickable: Disable ripple effect when clicking
 */
fun Modifier.noRippleClickable(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }
    ) {
        onClick()
    }
}