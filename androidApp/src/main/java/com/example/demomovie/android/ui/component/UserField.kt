package com.example.demomovie.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demomovie.android.R

@Composable
fun UserField(modifier: Modifier) {
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
            painter = painterResource(id = R.drawable.logo_movie),
            contentDescription = "logo_movie"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .weight(1f)
                .align(CenterVertically)
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
                .align(CenterVertically)
                .size(32.dp),
            painter = painterResource(id = R.drawable.ic_wish_list),
            contentDescription = "logo_movie"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserFieldPreview() {
    UserField(Modifier)
}