package com.example.shared_ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShareMovieDetail(
    modifier: Modifier,
    title: String?,
    posterPath: String?,
    overView: String?,
    onBackClick: () -> Unit
) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        BackDropImage(posterPath ?: "")
        Box(
            Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor.copy(alpha = .3f),
                        primaryColor,
                        primaryColor
                    )
                )
            )
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.Transparent)
                .padding(top = 24.dp)
                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp))
                            .clickable { onBackClick() },
                        painter = getBackIcon(),
                        contentDescription = "back"
                    )
                    Image(
                        modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)),
                        painter = getWishListIcon(),
                        contentDescription = "back"
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 88.dp).align(Alignment.Center),
                    text = title ?: "No title",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            PosterImage(posterPath ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                text = "Story Line",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = overView ?: "No overView",
                fontSize = 14.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
expect fun getBackIcon(): Painter

@Composable
expect fun getWishListIcon(): Painter

@Composable
expect fun PosterImage(posterPath: String)

@Composable
expect fun BackDropImage(posterPath: String)
