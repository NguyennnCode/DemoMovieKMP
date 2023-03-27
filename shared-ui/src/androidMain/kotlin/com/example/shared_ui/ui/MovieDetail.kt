package com.example.shared_ui.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shared_ui.R

@Composable
actual fun getBackIcon(): Painter =
    painterResource(R.drawable.ic_back)

@Composable
actual fun getWishListIcon(): Painter = painterResource(R.drawable.ic_wish_list)

@Composable
actual fun PosterImage(posterPath: String) {
    Card(
        modifier = Modifier.padding(horizontal = 88.dp).fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)), elevation = 8.dp
    ) {
        AsyncImage(
            modifier = Modifier,
            model = "https://image.tmdb.org/t/p/w500/${posterPath}",
            placeholder = painterResource(R.drawable.img_place_holder),
            contentDescription = "poster",
            contentScale = ContentScale.FillWidth
        )
    }

}

@Composable
actual fun BackDropImage(posterPath: String) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = "https://image.tmdb.org/t/p/w500/${posterPath}",
        contentDescription = "back drop",
        contentScale = ContentScale.FillWidth
    )
}