package com.vahitkeskin.jetpackcomposestackoverflowclone.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowScaffold
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.JetpackComposeStackoverflowCloneTheme
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains.APP_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStackoverflowCloneTheme(
                darkTheme = true
            ) {
                StackoverflowScaffold()
            }
        }
    }
}

@Preview(
    name = APP_NAME,
    showBackground = true,
    device = Devices.NEXUS_5X
)
@Composable
fun BottomBarStackOverflowPreview() {
    JetpackComposeStackoverflowCloneTheme(
        darkTheme = true
    ) {
        StackoverflowScaffold()
    }
}