@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication
import com.example.demomovie.ShareMovie
import com.example.demomovie.model.Movie
import com.example.shared_ui.SharedMovieItem
import com.example.shared_ui.SharedSearchField
import com.example.shared_ui.SharedUserField
import com.example.shared_ui.ui.blue12CDD9
import kotlinx.coroutines.runBlocking
import ui.loadImage
import ui.primaryColor

val montserratFont = FontFamily(
    Font("fonts/montserrat_bold.ttf", FontWeight.Bold),
    Font("fonts/montserrat_regular.ttf", FontWeight.Normal),
    Font("fonts/montserrat_semi_bold.ttf", FontWeight.SemiBold),
    Font("fonts/montserrat_extra_bold.ttf", FontWeight.ExtraBold),
    Font("fonts/montserrat_medium.ttf", FontWeight.Medium),
)
val typography = Typography(
    defaultFontFamily = montserratFont,
)

fun main() = singleWindowApplication {

    val state = rememberLazyListState()
    var movies: List<Movie>
    runBlocking {
        movies = ShareMovie().getMovies("upcoming")
    }
    MaterialTheme(
        typography = typography
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(primaryColor).verticalScroll(
                rememberScrollState()
            ),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            //User Field
            SharedUserField(
                modifier = Modifier.padding(horizontal = 24.dp),
                avatarPainter = painterResource("logo.svg"),
                wishListPainter = painterResource("ic_wish_list.png")
            )

            //Search Field
            Spacer(modifier = Modifier.height(32.dp))
            SharedSearchField(
                modifier = Modifier,
                searchIcon = painterResource("ic_search.png"),
                filterIcon = painterResource("ic_filter.png"),
            )

            //Top Banner Field
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "UP COMING",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                items(movies) {
                    var active by remember { mutableStateOf(false) }
                    BannerItem(
                        modifier = Modifier
                            .onPointerEvent(PointerEventType.Enter) { active = true }
                            .onPointerEvent(PointerEventType.Exit) { active = false },
                        title = it.title,
                        pathImg = it.backdropPath,
                        releaseDate = it.releaseDate,
                        overView = it.overview,
                        enableOverView = active
                    )
                }
            }

            //Categories
            Spacer(modifier = Modifier.height(32.dp))
            CategoryField(modifier = Modifier, title = "Top Rated", category = "top_rated")
            Spacer(modifier = Modifier.height(32.dp))
            CategoryField(modifier = Modifier, title = "Now Playing", category = "now_playing")
            Spacer(modifier = Modifier.height(32.dp))
            CategoryField(modifier = Modifier, title = "Popular", category = "popular")
        }
    }
}

@Composable
private fun CategoryField(modifier: Modifier, title: String, category: String) {
    var movies: List<Movie>
    runBlocking {
        movies = ShareMovie().getMovies(category)
    }
    Column(modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth().then(modifier)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                text = title,
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
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(movies) {
                SharedMovieItem(
                    modifier = Modifier,
                    imgPoster = {
                        Image(
                            bitmap = loadImage("https://image.tmdb.org/t/p/w500/${it.posterPath}"),
                            contentDescription = "banner post",
                            contentScale = ContentScale.FillWidth
                        )
                    },
                    imgRate = painterResource("img_rate.png"),
                    title = it.title ?: "",
                    overview = it.overview ?: ""
                )
            }
        }
    }
}

@Composable
private fun BannerItem(
    modifier: Modifier,
    title: String?,
    pathImg: String?,
    releaseDate: String?,
    overView: String?,
    enableOverView: Boolean
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .width(IntrinsicSize.Min)
            .then(modifier),
    ) {
        Box(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier.heightIn(min = 100.dp),
                bitmap = loadImage("https://image.tmdb.org/t/p/w500/$pathImg"),
                contentDescription = "banner post",
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(horizontal = 16.dp)
            ) {
                Text(
                    text = title ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = releaseDate ?: "", fontSize = 12.sp, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        AnimatedVisibility(enableOverView) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                text = overView ?: "",
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}