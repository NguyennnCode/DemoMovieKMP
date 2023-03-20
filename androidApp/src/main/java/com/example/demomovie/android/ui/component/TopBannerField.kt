@file:OptIn(ExperimentalPagerApi::class)

package com.example.demomovie.android.ui.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.demomovie.android.R
import com.example.demomovie.android.extension.shake
import com.example.demomovie.android.theme.blue12CDD9
import com.example.demomovie.android.theme.secondaryColor
import com.example.demomovie.model.Movie
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun TopBannerField(modifier: Modifier, movies: List<Movie>) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(),
            state = pagerState,
            count = movies.size,
            contentPadding = PaddingValues(horizontal = 60.dp),
        ) {
            Banner(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(it).absoluteValue
                        lerp(
                            start = 0.9f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                pathImg = movies[it].backdropPath ?: "",
                title = movies[it].title ?: "",
                releaseDate = movies[it].releaseDate ?: "",
                isGoodChoice = it == 0
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            IndicatorPager(
                modifier = Modifier.align(Alignment.Center),
                pageState = pagerState,
            )
        }
    }
}

@Composable
private fun Banner(
    modifier: Modifier,
    pathImg: String,
    title: String,
    releaseDate: String,
    isGoodChoice: Boolean = false,
) {
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(350.dp)

                .clip(RoundedCornerShape(16.dp)),
            model = "https://image.tmdb.org/t/p/w500/$pathImg",
            contentDescription = "banner post",
            contentScale = ContentScale.Crop
        )
        if (isGoodChoice) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_good_choice),
                contentDescription = "good choice"
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = releaseDate, fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun IndicatorPager(
    modifier: Modifier,
    pageState: PagerState,
) {
    val widthItem = 36.dp
    val widthItemPx = with(LocalDensity.current) { widthItem.roundToPx() }

    val widthLayout = widthItem * 5
    val widthLayoutPx = with(LocalDensity.current) { widthLayout.roundToPx() }

    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val selectedIndex by remember { derivedStateOf { pageState.currentPage } }

    LazyRow(
        modifier = Modifier
            .width(widthLayout)
            .height(36.dp)
            .then(modifier),
        state = listState
    ) {
        items(pageState.pageCount) {
            NumberIndicator(
                modifier = Modifier.width(widthItem),
                text = "${it + 1}",
                isSelected = { selectedIndex == it },
                index = it
            ) {
                scope.launch {
                    pageState.animateScrollToPage(it)
                }
            }
        }
    }

    LaunchedEffect(key1 = selectedIndex) {
        val viewportSize = listState.layoutInfo.viewportSize
        listState.animateScrollToItem(
            selectedIndex,
            (widthLayoutPx / 2 + widthItemPx / 2 - viewportSize.width)
        )
    }
}

@Composable
private fun NumberIndicator(
    modifier: Modifier,
    text: String,
    isSelected: () -> Boolean = { false },
    index: Int,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val painter = remember { getPainter(index) }

    val isSelectedState by remember { derivedStateOf { isSelected() } }
    val transition = updateTransition(isSelectedState, label = "CategoryItem state")

    val animTextColor by transition.animateColor(label = "text color") {
        if (it) blue12CDD9 else blue12CDD9.copy(
            alpha = .32f
        )
    }
    val animBackgroundColor by transition.animateColor(label = "color background") { if (it) secondaryColor else Color.Transparent }
    val animScale by transition.animateFloat(label = "text Scale") { if (it) 1f else .5f }

    Box(
        modifier = Modifier
            .drawBehind {
                drawCircle(
                    color = animBackgroundColor,
                )
            }
            .graphicsLayer {
                scaleX = animScale
                scaleY = animScale
                alpha = animScale
            }
            .clickable(
                enabled = !isSelectedState,
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
            .then(modifier),
    ) {
        if (painter == null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                color = animTextColor,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        } else {
            Image(
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center)
                    .shake(isSelectedState),
                painter = painterResource(id = painter),
                contentDescription = "icon search"
            )
        }
    }
}


private fun getPainter(index: Int): Int? {
    return when (index) {
        0 -> R.drawable.ic_champion
        1 -> R.drawable.ic_second_champion
        2 -> R.drawable.ic_third_champion
        else -> null
    }
}
