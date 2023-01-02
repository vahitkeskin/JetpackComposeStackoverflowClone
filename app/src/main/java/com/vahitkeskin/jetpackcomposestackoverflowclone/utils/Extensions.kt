package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Base64
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains.UTF_8
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10

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

fun String.copyText(context: Context){
    val myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val myClip: ClipData = ClipData.newPlainText("Label", this)
    myClipboard.setPrimaryClip(myClip)
}

fun prettyCount(number: Number): String? {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = number.toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            numValue / Math.pow(
                10.0,
                (base * 3).toDouble()
            )
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}