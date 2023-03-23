package com.example.shared_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shared_ui.ui.secondaryColor

@Composable
fun SharedUserField(
    modifier: Modifier,
    avatarPainter: Painter,
    wishListPainter: Painter
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(4.dp))
                .padding(5.dp),
            painter = avatarPainter,
            contentDescription = "logo_movie"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Hello, TheMovieDB",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = "Let’s stream your favorite movie",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(32.dp),
            painter = wishListPainter,
            contentDescription = "logo_movie"
        )
    }
}


@Composable
fun SharedSearchField(
    modifier: Modifier,
    searchIcon: Painter,
    filterIcon: Painter,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(secondaryColor)
            .height(40.dp)
            .padding(12.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = searchIcon,
            contentDescription = "icon search"
        )

        Spacer(modifier = Modifier.width(8.dp))


        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = "Search a title...",
            fontSize = 14.sp,
            color = Color.Gray,
        )

        Divider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
                .width(1.dp),
            color = Color.Gray
        )

        Image(
            modifier = Modifier.size(16.dp),
            painter = filterIcon,
            contentDescription = "icon search"
        )
    }
}

@Composable
fun SharedMovieItem(
    modifier: Modifier,
    title: String,
    imgPoster: @Composable () -> Unit,
    imgRate: Painter,
    overview: String
) {
    BoxWithConstraints {
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
                    imgPoster()

                    Image(
                        modifier = Modifier
                            .padding(all = 8.dp)
                            .height(20.dp)
                            .align(Alignment.TopEnd),
                        painter = imgRate,
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
}