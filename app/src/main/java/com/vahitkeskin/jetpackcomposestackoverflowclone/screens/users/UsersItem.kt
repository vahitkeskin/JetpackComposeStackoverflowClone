package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue

/**
 * @authot: Vahit Keskin
 * creared on 31.12.2022
 */

@Composable
fun UsersItem(item: Item) {
    println("UsersItem item name: ${item.display_name} and id: $")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 6.dp, end = 15.dp, start = 15.dp),
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
                Text(text = item.display_name.orEmpty(), color = StackoverflowBlue)
                Text(text = item.location.orEmpty())
                Text(text = item.reputation.toString().orEmpty())
            }
        }
    }
}