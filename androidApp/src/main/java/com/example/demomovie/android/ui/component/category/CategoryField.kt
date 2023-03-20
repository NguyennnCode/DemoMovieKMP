package com.example.demomovie.android.ui.component.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.demomovie.android.R
import com.example.demomovie.android.theme.secondaryColor
import com.example.demomovie.model.Movie


@Composable
fun CategoryField(
    modifier: Modifier,
    movies: List<Movie>
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
                MovieItem(
                    modifier = Modifier,
                    imgPath = it.posterPath ?: "",
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

@Composable
private fun MovieItem(
    modifier: Modifier,
    imgPath: String,
    title: String,
    overview: String
) {
    Box(
        modifier = Modifier
            .width(135.dp)
            .height(230.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(secondaryColor)
            .then(modifier)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = "https://image.tmdb.org/t/p/w500/$imgPath",
                    contentDescription = "poster",
                    contentScale = ContentScale.FillBounds
                )

                Image(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .height(20.dp)
                        .align(Alignment.TopEnd),
                    painter = painterResource(id = R.drawable.img_rate),
                    contentDescription = "rate",
                    contentScale = ContentScale.FillHeight
                )

            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = overview,
                color = Color.Gray,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}