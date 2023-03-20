package com.example.shared_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SharedSearchField(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black)
            .height(40.dp)
            .padding(12.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
//        Image(
//            modifier = Modifier.size(20.dp),
//            painter = painterResource(id = R.drawable.ic_search),
//            contentDescription = "icon search"
//        )

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

//        Image(
//            modifier = Modifier.size(16.dp),
//            painter = painterResource(id = R.drawable.ic_filter),
//            contentDescription = "icon search"
//        )
    }
}