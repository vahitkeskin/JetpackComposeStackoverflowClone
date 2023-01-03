package com.vahitkeskin.jetpackcomposestackoverflowclone.component

import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size

/**
 * @authot: Vahit Keskin
 * creared on 4.01.2023
 */
@Composable
fun StackoverflowGifIcon(
    context: Context,
    @DrawableRes icon: Int
) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(25.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Transparent, CircleShape),
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(data = icon)
                .apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
        ),
        contentDescription = null
    )
}