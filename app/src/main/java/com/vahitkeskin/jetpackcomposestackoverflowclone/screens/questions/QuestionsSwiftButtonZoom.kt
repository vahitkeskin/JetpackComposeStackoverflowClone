package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.QuestionsZoomScreenSkip
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowOrange

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
@Composable
fun QuestionsSwiftButtonZoom() {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 60.dp, vertical = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_sign_ok),
                contentDescription = null
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.text_zoom))
                    addStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 52.sp,
                            fontFamily = FontFamily.Cursive
                        ),
                        start = 0,
                        end = 1
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 20.sp,
                style = TextStyle(
                    textAlign = TextAlign.Justify
                ),
                color = StackoverflowOrange
            )
        }
        QuestionsZoomScreenSkip()
    }
}

@Preview(
    name = "Questions Swift Button Zoom",
    device = Devices.PIXEL_4,
    showBackground = true,
    backgroundColor = 0xFF2D2D2D,
    showSystemUi = true,
)
@Composable
fun QuestionsSwiftButtonZoomPreview() {
    QuestionsSwiftButtonZoom()
}