package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.prettyCount
import kotlinx.coroutines.*

/**
 * @authot: Vahit Keskin
 * creared on 31.12.2022
 */

@OptIn(ExperimentalMotionApi::class)
@Composable
fun UsersItem(item: Item, selected : Boolean = false, clickAction: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var mySelected by remember { mutableStateOf(selected) }
    var job: Job? = null
    var progress by remember {
        mutableStateOf(0f)
    }
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    fun selectedItemAnimation() {
        job = coroutineScope.launch(Dispatchers.Main.immediate) {
            if (progress > 0.0f) {
                while (progress > 0.0f) {
                    progress -= 0.1f
                    delay(40)
                }
                if (progress < 0.1f) {
                    mySelected = false
                }
            } else {
                while (progress < 1.0f) {
                    progress += 0.1f
                    mySelected = true
                    delay(40)
                }
            }
        }
    }

    if (mySelected) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp)
                .clickable {
                    selectedItemAnimation()
                },
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            MotionLayout(
                motionScene = MotionScene(content = motionScene),
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.profile_image),
                    contentDescription = null,
                    modifier = Modifier.layoutId("profile_pic")
                )
                Column(
                    modifier = Modifier.layoutId("username")
                ) {
                    Text(
                        text = item.display_name.orEmpty(),
                        fontSize = 24.sp,
                        color = StackoverflowBlue
                    )
                    Text(
                        text = Html.fromHtml(item.about_me.orEmpty()).toString(),
                        fontSize = 16.sp,
                        color = StackoverflowBlue
                    )
                }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp)
                .clickable {
                    clickAction()
                    selectedItemAnimation()
                },
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = rememberAsyncImagePainter(model = item.profile_image),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column {
                    Text(text =  item.display_name.orEmpty(), color = StackoverflowBlue)
                    Text(text = item.location.orEmpty())
                    Text(text = prettyCount(item.reputation).orEmpty())
                }
            }
        }
    }
}