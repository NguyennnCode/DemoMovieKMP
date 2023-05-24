package com.example.demomovie.android.ui.component.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.demomovie.android.R
import com.example.demomovie.model.Movie
import com.example.shared_ui.SharedMovieItem


@Composable
fun CategoryField(
    modifier: Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit
) {
    if (movies.isNotEmpty()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(movies) {
                SharedMovieItem(
                    modifier = Modifier.clickable { onClick(it) },
                    imgPoster = {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                            contentDescription = "poster",
                            contentScale = ContentScale.FillBounds
                        )
                    },
                    imgRate = painterResource(id = R.drawable.img_rate),
                    title = it.title ?: "",
                    overview = it.overview ?: ""
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "No content here!!....",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}