package com.vahitkeskin.jetpackcomposestackoverflowclone.screens.homedetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import coil.compose.rememberAsyncImagePainter
import com.vahitkeskin.jetpackcomposestackoverflowclone.R
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowGifIcon
import com.vahitkeskin.jetpackcomposestackoverflowclone.component.StackoverflowMarqueeText
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.home.HomeScreenTagsItem
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowBlue
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowDark
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowPointUnSelect
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.noRippleClickable
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
    val context = LocalContext.current
    var pointClick by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()
    var score by remember { mutableStateOf(viewState.data?.score) }

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

    val mTitle = Jsoup.parse(viewState.data?.body.orEmpty()).select(Contains.HTML_CSS_QUERY_P).text()
    val mCode = Jsoup.parse(viewState.data?.body.orEmpty()).select(Contains.HTML_CSS_QUERY_CODE).text()
    val image = Jsoup.parse(viewState.data?.body.orEmpty()).select(Contains.HTML_CSS_QUERY_IMG)
        .first()?.absUrl(Contains.HTML_CSS_QUERY_SRC)

    Box {
        if (mTitle.isNotEmpty() || mCode.isNotEmpty() || image?.isNotEmpty() == true) {
            Column(
                modifier = Modifier
                    .background(StackoverflowDark)
                    .fillMaxSize()
                    .padding(top = 5.dp, bottom = 10.dp, end = 10.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.background(StackoverflowDark),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_point_up),
                            modifier = Modifier.noRippleClickable {
                                pointClick = 1
                                if ((score ?: 0) <= (viewState.data?.score ?: 0)) {
                                    score = score?.plus(1)
                                    Toast.makeText(context, "Ops! This change is not set \uD83D\uDE03", Toast.LENGTH_SHORT).show()
                                }
                            },
                            contentDescription = null,
                            tint = if (pointClick == 0 || pointClick == 2 || score == viewState.data?.score) {
                                StackoverflowPointUnSelect
                            } else {
                                StackoverflowPointSelect
                            }
                        )
                        Text(text = score.toString(), modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                        Icon(
                            painterResource(id = R.drawable.ic_point_down),
                            modifier = Modifier.noRippleClickable {
                                pointClick = 2
                                if ((score ?: 0) >= (viewState.data?.score ?: 0)) {
                                    score = score?.minus(1)
                                    Toast.makeText(context, "Ops! This change is not set \uD83D\uDE03", Toast.LENGTH_SHORT).show()
                                }
                            },
                            contentDescription = null,
                            tint = if (pointClick == 0 || pointClick == 1 || score == viewState.data?.score) {
                                StackoverflowPointUnSelect
                            } else {
                                StackoverflowPointSelect
                            }
                        )
                    }
                    Column {
                        Text(text = viewState.data?.title.orEmpty(), fontSize = 24.sp)
                        Spacer(modifier = Modifier.padding(10.dp))
                        if (!image.isNullOrEmpty()) {
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

                        if (!(mCode == "" || mCode.isEmpty() || mCode.isBlank())) {
                            AndroidView(
                                modifier = Modifier
                                    .background(StackoverflowDark)
                                    .fillMaxWidth(),
                                factory = { CodeView(it) },
                                update = { codeView ->
                                    codeView.theme = Theme.AGATE
                                    codeView.code = mCode
                                    codeView.language = Language.KOTLIN
                                    codeView.isWrapLine = true
                                    codeView.isZoomEnabled = true
                                    codeView.isShowLineNumber = true
                                    codeView.startLineNumber = 0
                                    codeView.fontSize = 0.5f
                                    codeView.apply()
                                }
                            )
                        }

                        viewState.data?.let { mItem ->
                            LazyRow(
                                modifier = Modifier.padding(end = 5.dp, top = 10.dp)
                            ) {
                                items(mItem.tags) { mItem ->
                                    HomeScreenTagsItem(tags = mItem)
                                }
                            }
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                                    .background(StackoverflowDark),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(end = 5.dp),
                                    painter = rememberAsyncImagePainter(model = mItem.owner.profile_image),
                                    contentDescription = null
                                )
                                Column {
                                    Text(text = mItem.owner.display_name, color = StackoverflowBlue)
                                    Text(text = mItem.owner.reputation.toString(), color = StackoverflowPointUnSelect)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StackoverflowGifIcon(
                    icon = R.drawable.no_search_results_found_gif,
                    previewPage = Contains.QUESTION_SCREEN_ITEM_SIZE
                )
                StackoverflowMarqueeText(Contains.TEXT_EMPTY_DETAIL)
            }
        }
    }
}