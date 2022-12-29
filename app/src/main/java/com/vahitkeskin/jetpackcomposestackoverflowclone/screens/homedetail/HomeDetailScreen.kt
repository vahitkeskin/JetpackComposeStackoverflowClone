package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.homedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowDark
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeDetailViewEvent
import com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel.HomeDetailViewModel
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */

@Composable
fun HomeDetailScreen(
    viewModel: HomeDetailViewModel = viewModel(),
) {

    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is HomeDetailViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

    val mTitle = Jsoup.parse(viewState.data?.body.orEmpty()).select("p").text()
    val mCode = Jsoup.parse(viewState.data?.body.orEmpty()).select("code").text()
    val image = Jsoup.parse(viewState.data?.body.orEmpty()).select("img").first()?.absUrl("src")

    Column(
        modifier = Modifier
            .background(StackoverflowDark)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        if (!image.isNullOrEmpty() ) {
            Image(
                modifier = Modifier
                    .height(height = 200.dp)
                    .padding(5.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
        }
        Text(text = mTitle)
        Spacer(modifier = Modifier.padding(10.dp))

        AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = { CodeView(it) },
            update = { codeView ->
                codeView.theme = Theme.AGATE
                codeView.code = mCode
                codeView.language = Language.GRADLE
                codeView.isWrapLine = true
                codeView.isZoomEnabled = true
                codeView.isShowLineNumber = true
                codeView.startLineNumber = 0
                codeView.fontSize = 0.5f
                codeView.apply()
            }
        )
    }
}