@file:OptIn(ExperimentalAnimationApi::class)

package com.example.demomovie.android.ui.component.category

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demomovie.android.enums.Category
import com.example.demomovie.android.theme.blue12CDD9
import com.example.demomovie.android.theme.secondaryColor
import com.example.demomovie.model.Movie

@Composable
fun CategoriesField(
    modifier: Modifier,
    onChangeCategory: (category: String) -> Unit,
    categoryMovies: List<Movie>,
    onGoDetail: (Movie) -> Unit
) {
    val categories = Category.values()

    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
    {
        Text(
            modifier = Modifier.padding(start = 28.dp),
            text = "Movie",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(
            modifier = Modifier,
            contentPadding = PaddingValues(horizontal = 28.dp)
        ) {
            items(categories.size) {
                CategoryHeaderItem(
                    modifier = Modifier,
                    title = getCategoryTitle(categories[it]),
                    isSelected = { selectedIndex == it }
                ) {
                    selectedIndex = it
                    Log.d("Demo", ": selectedIndex $selectedIndex")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedContent(targetState = selectedIndex,
            transitionSpec = {
                EnterTransition.None with ExitTransition.None
            }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Text(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = scaleIn(),
                            exit = scaleOut()
                        )
                        .align(Alignment.CenterStart),
                    text = remember { getCategoryTitle(categories[selectedIndex]) },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = "See all",
                    fontSize = 14.sp,
                    color = blue12CDD9
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedContent(targetState = selectedIndex, transitionSpec = {
            slideInVertically { it } with slideOutVertically { -it }
        }) {
            CategoryField(
                modifier = Modifier.animateEnterExit(
                    enter = fadeIn(),
                    exit = fadeOut()
                ),
                movies = categoryMovies,
            ) {
                onGoDetail(it)
            }
        }
    }

    LaunchedEffect(key1 = selectedIndex) {
        onChangeCategory(categories[selectedIndex].category)
    }
}

private fun getCategoryTitle(category: Category): String {
    return when (category) {
        Category.TOP_RATED -> "Top Rated"
        Category.NOW_PLAYING -> "Now Playing"
        Category.LATEST -> "Latest"
        Category.POPULAR -> "Popular"
        Category.UP_COMING -> "Upcoming"
    }
}

@Composable
private fun CategoryHeaderItem(
    modifier: Modifier,
    title: String,
    isSelected: () -> Boolean = { false },
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val isSelectedState by remember { derivedStateOf { isSelected() } }
    val transition = updateTransition(isSelectedState, label = "CategoryItem state")

    val animTextColor by transition.animateColor(label = "text color") { if (it) blue12CDD9 else Color.White }
    val animBackgroundColor by transition.animateColor(label = "color background") { if (it) secondaryColor else Color.Transparent }

    Text(
        modifier = Modifier
            .widthIn(100.dp)
            .drawBehind {
                drawRoundRect(
                    color = animBackgroundColor,
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
            }
            .padding(vertical = 8.dp, horizontal = 8.dp)

            .clickable(
                enabled = !isSelectedState,
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
            .then(modifier),
        text = title,
        fontWeight = FontWeight.SemiBold,
        color = animTextColor,
        textAlign = TextAlign.Center
    )
}