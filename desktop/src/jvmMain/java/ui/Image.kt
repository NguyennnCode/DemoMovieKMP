package ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.net.URL

fun loadImage(url: String): ImageBitmap {
    return Image.makeFromEncoded(URL(url).readBytes())
        .toComposeImageBitmap()
}