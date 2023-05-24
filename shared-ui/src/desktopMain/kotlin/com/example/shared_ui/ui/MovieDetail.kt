package com.example.shared_ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.skia.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.net.URL

@Composable
actual fun getBackIcon(): Painter = painterResource("ic_back.png")

@Composable
actual fun getWishListIcon(): Painter = painterResource("ic_wish_list.png")
@Composable
actual fun PosterImage(posterPath: String) {
    Image(
        modifier = Modifier.padding(horizontal = 88.dp),
        bitmap = loadImage("https://image.tmdb.org/t/p/w500//${posterPath}"),
        contentDescription = "banner post",
        contentScale = ContentScale.FillWidth
    )
}

fun loadImage(url: String): ImageBitmap {
    return Image.makeFromEncoded(URL(url).readBytes())
        .toComposeImageBitmap()
}

@Composable
actual fun BackDropImage(posterPath: String) {
    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = loadImage("https://image.tmdb.org/t/p/w500//${posterPath}"),
        contentDescription = "banner post",
        contentScale = ContentScale.FillWidth
    )
}