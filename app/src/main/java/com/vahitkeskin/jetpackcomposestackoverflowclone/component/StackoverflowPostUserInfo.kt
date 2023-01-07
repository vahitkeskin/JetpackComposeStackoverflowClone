package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.prettyCount
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.whenTime

/**
 * @authot: Vahit Keskin
 * creared on 7.01.2023
 */
@Composable
fun StackoverflowPostUserInfo(item: Item) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = StackoverflowBlue, fontSize = 14.sp)) {
                append(item.owner.display_name)
            }
            withStyle(style = SpanStyle(color = Color.LightGray, fontSize = 14.sp)) {
                append(" " + prettyCount(item.owner.reputation).orEmpty())
            }
            withStyle(style = SpanStyle(Color.Gray, fontSize = 12.sp)) {
                append(Contains.ASKED)
            }
            withStyle(style = SpanStyle(Color.Gray, fontSize = 12.sp)) {
                append(" " + item.last_edit_date.whenTime())
            }
        }
    )
}