package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
@Composable
fun StackoverflowSixDottedLines(text: String) {
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    Text(
        fontSize = 25.sp,
        text = text,
        style = MaterialTheme.typography.h3,
        color = Color.Red,
        onTextLayout = {
            layout = it
        },
        modifier = Modifier
            .padding(30.dp)
            .drawBehind {
                layout?.let {
                    val thickness = 5f
                    val dashPath = 15f
                    val spacingExtra = 4f
                    val offsetY = 6f

                    for (i in 0 until it.lineCount) {
                        drawPath(
                            path = Path().apply {
                                moveTo(it.getLineLeft(i), it.getLineBottom(i) - spacingExtra + offsetY)
                                lineTo(it.getLineRight(i), it.getLineBottom(i) - spacingExtra + offsetY)
                            },
                            color = if (i == 4) Color.White else Color.Red,
                            style = Stroke(width = thickness,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPath, dashPath), 0f)
                            )
                        )
                    }
                }
            }
    )
}